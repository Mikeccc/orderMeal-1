package app.cn.qtt.bm.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TSendMmsBuffer;

public class SmilUtils {
	protected static CCrppLog4j gLogger = new CCrppLog4j((SmilUtils.class).toString());

	/***
	 * 生成Smil文件
	 * 
	 * @param dirName
	 *            批次号
	 * @param filesPath
	 *            附件
	 * @param lastFrameFilePath
	 *            每个批次尾帧路径
	 * @return String
	 */
	public static String markDefaultSmil(String dirName, String filesPath, String lastFrameFilePath) {

		String smilFileList = "";
		try {
			// 生成smil文件
			String smilPath = CacheConstants.getParamValueByName(Constants.SMIL_FILEPATH) + dirName + "/";

			File file = new File(smilPath);
			if (!file.exists()) {
				file.mkdir();
			}
			gLogger.info("smilPath ==" + smilPath);
			smilFileList = SmilUtils.createSmilFile(filesPath, smilPath, lastFrameFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return smilFileList;
	}

	/**
	 * @param price
	 * @author GeYanmeng
	 * @date 2013-6-26
	 */
	public static String generateMmsTextFile(TOrderInfoShop orderInfoShop, TSendMmsBuffer sendMmsBuffer) {
		String mmsFilePath = "";
		try {
			String filePath = CacheConstants.getParamValueByName(Constants.SMIL_FILEPATH)
					+ sendMmsBuffer.getMmsFeginCode() + "/";

			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
			
			//将二维码图片拷贝到新目录下面
			File oldFile = new File(sendMmsBuffer.getMmsFilePath());
			FileUtils.copyFileToDirectory(oldFile,file);
			
			//获取新目录下的图片文件名
			File[] files = file.listFiles();
			File newFilePath = null;
			if(files.length != 0){
				newFilePath = files[0];
			}else{
				throw new Exception("目录下没有文件");
			}
			
			File newFileTemp = new File(file.getAbsolutePath()+"/"+"1."+newFilePath.getName().substring(newFilePath.getName().lastIndexOf(".")+1));
			newFilePath.renameTo(newFileTemp);
			
			final String txtFilePathUp = filePath + "2.txt";

			// 先创建一个空文件
			File txtFile1 = new File(txtFilePathUp);
			if (!txtFile1.exists()) {
				txtFile1.createNewFile();
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());

			FileWriter writer = new FileWriter(txtFilePathUp, false);
			writer.write("客官您好，" + calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH)+1) + "月"
					+ calendar.get(Calendar.DAY_OF_MONTH) + "日，您在“"+orderInfoShop.getShopInfo().getShopName()+"”已成功订餐，订单总金额为" + orderInfoShop.getPrice()
					+ "元，到时带上您的钱财，凭二维码领餐吧。扫描时使用二维码小图即可，放大后可能会不易识别哦。"+"如需查看订单详细，请访问" 
					+ CacheConstants.getParamValueByName(Constants.WAP_URL)
					+ "orderId="
					+ orderInfoShop.getOrderId());
			writer.close();


			mmsFilePath =  filePath+newFileTemp.getName()
					 + Constants.MMS_ATTACHFILES_SPLIT_CODE+txtFilePathUp ;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mmsFilePath;
	}
	
	/**
	 * @param orderInfoShop
	 * @param sendMmsBuffer
	 * @return
	 * @author Gabriel
	 * @createtime 2013-9-18 上午9:13:27
	 */
	public static String generateSmsContent(TOrderInfoShop orderInfoShop, TSendMmsBuffer sendMmsBuffer) {
		StringBuffer sb = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		sb.append("客官您好，").append(calendar.get(Calendar.YEAR)).append("年").append(calendar.get(Calendar.MONTH) + 1)
				.append("月").append(calendar.get(Calendar.DAY_OF_MONTH)).append("日，您在")
				.append(orderInfoShop.getShopInfo().getShopName()).append("“已成功订餐，订单总金额为").append(orderInfoShop.getPrice())
				.append("元，领餐验证码为：").append(orderInfoShop.getCaptchas()).append("。领餐时，可使用二维码，也可使用此验证码进行领餐。");

		return sb.toString();
	}
	
	private static boolean isTxtFile(String fileName) {
		boolean result = false;
		if (fileName.toLowerCase().endsWith(".txt")) {
			result = true;
		}
		return result;
	}

	private static boolean isVedioFile(String fileName) {
		boolean result = false;
		if (fileName.toLowerCase().endsWith(".3gp")) {
			result = true;
		}
		return result;
	}

	private static boolean isAudioFile(String fileName) {
		boolean result = false;
		if (fileName.toLowerCase().endsWith(".mid") || fileName.toLowerCase().endsWith(".amr")) {
			result = true;
		}
		return result;
	}

	/**
	 * 生成smil 文件
	 * 
	 * @param filelists
	 * @param smilPath
	 * @param lastFrameFilePath
	 * @return
	 * @throws Exception
	 */
	private static String createSmilFile(String filesPath, String smilPath, String lastFrameFilePath) throws Exception {
		String xFunctionName = "createSmilFile";
		gLogger.begin(xFunctionName);
		StringBuilder newContent = new StringBuilder();
		// 1、创建一个空的xml文档
		Document document = DocumentHelper.createDocument();
		// 2、创建名字为smil的根节点
		Element smilElement = document.addElement("smil");
		// 3、创建名字为head的一级节点
		Element headElement = smilElement.addElement("head");
		// 4、在一级节点head底下创建名字为layout的二级节点
		Element layoutElement = headElement.addElement("layout");
		// 5、在二级节点layout底下创建名字为root-layout的二级节点
		layoutElement.addElement("root-layout").addAttribute("height", "128").addAttribute("width", "128");
		// 6、在二级节点layout底下创建名字为region的二级节点
		layoutElement.addElement("region").addAttribute("id", "text").addAttribute("height", "100%")
				.addAttribute("width", "100%").addAttribute("left", "0%").addAttribute("top", "0%");
		// 7、在二级节点layout底下创建名字为region的二级节点
		layoutElement.addElement("region").addAttribute("id", "image").addAttribute("height", "100%")
				.addAttribute("width", "100%").addAttribute("left", "0%").addAttribute("top", "0%");
		// 8、创建名字为body的一级节点
		Element bodyElement = smilElement.addElement("body");

		int index = 1;
		newContent.append(filesPath);
		newContent.append(";");
		String[] newFiles = StringUtils.split(filesPath, Constants.MMS_ATTACHFILES_SPLIT_CODE);
		if (newFiles != null) {
			Element parElement = bodyElement.addElement("par");
			String dur = "10s";
			for (String filePath : newFiles) {
				if (filePath.toLowerCase().endsWith(".3gp")) {
					dur = "100s";
					break;
				} else if (filePath.toLowerCase().endsWith(".mid")) {
					dur = "100s";
					break;
				} else if (filePath.toLowerCase().endsWith(".amr")) {
					dur = "100s";
					break;
				} else if (filePath.toLowerCase().endsWith(".txt")) {
					dur = "20s";
				}
			}
			parElement.addAttribute("dur", dur);
			for (String filePath : newFiles) {
				File file = new File(filePath);
				if (file.exists()) {
					StringBuilder src = new StringBuilder("cid:").append(index++);
					String fileName = file.getName();
					// 判断是否为文本文件
					if (isTxtFile(fileName)) {
						src.append(fileName.substring(fileName.lastIndexOf('.'), fileName.length()));
						parElement.addElement("text").addAttribute("src", src.toString())
								.addAttribute("region", "text");
					}// 判断是否为3gp格式视频文件
					else if (isVedioFile(fileName)) {
						src.append(fileName.substring(fileName.lastIndexOf('.'), fileName.length()));
						parElement.addElement("video").addAttribute("src", src.toString())
								.addAttribute("region", "image").addAttribute("fill", "freeze");
					} else if (isAudioFile(fileName)) {
						src.append(fileName.substring(fileName.lastIndexOf('.'), fileName.length()));
						parElement.addElement("audio").addAttribute("src", src.toString());
					} else {
						src.append(fileName.substring(fileName.lastIndexOf('.'), fileName.length()));
						parElement.addElement("img").addAttribute("src", src.toString())
								.addAttribute("region", "image");
					}
				}
			}
		}

		// 添加最后一帧信息
		if (StringUtils.isNotBlank(lastFrameFilePath) && lastFrameFilePath.toLowerCase().endsWith(".txt")) {
			// 读取最后一帧文件信息
			File file = new File(lastFrameFilePath);
			gLogger.info(" lastFrameFile length :" + file.length());
			// 判断该文件是否存在
			if (file.exists() && (file.length() != 0)) {
				gLogger.info("增加最后一帧信息");
				bodyElement.addElement("par").addAttribute("dur", "10s").addElement("text")
						.addAttribute("src", "cid:" + file.getName()).addAttribute("region", "text");
			}
		}

		// 输出smil文件信息
		final String smilFilePath = smilPath + getLineTimeStamp() + Random() + ".smil";
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(smilFilePath), "utf-8");
		String smilContent = smilElement.asXML().replaceAll("><", ">\n<");
		gLogger.info("smil content is -> \n" + smilContent);
		osw.write(smilContent);
		osw.flush();
		osw.close();

		if (StringUtils.isNotBlank(smilFilePath)) {
			newContent.append(smilFilePath);
		}
		String filesList = newContent.toString();
		gLogger.info("new file list is:" + filesList);

		return filesList;
	}

	private static String getLineTimeStamp() {
		String retDate = "";
		String padString = "";
		Calendar fullCalendar = Calendar.getInstance();

		retDate = Integer.toString(fullCalendar.get(Calendar.YEAR))
				+ Integer.toString(fullCalendar.get(Calendar.MONTH) + 1)
				+ Integer.toString(fullCalendar.get(Calendar.DATE));
		padString = Integer.toString(fullCalendar.get(Calendar.HOUR_OF_DAY));
		retDate += zeroPad(2, padString);
		padString = Integer.toString(fullCalendar.get(Calendar.MINUTE));
		retDate += zeroPad(2, padString);
		padString = Integer.toString(fullCalendar.get(Calendar.SECOND));
		retDate += zeroPad(2, padString) + "_";
		padString = Integer.toString(fullCalendar.get(Calendar.MILLISECOND));
		retDate += zeroPad(2, padString);
		return retDate;
	}

	/**
	 * 字符串前面补零
	 * 
	 * @param length
	 * @param toPad
	 * @return String
	 * @author GeYanmeng
	 * @date 2013-6-24
	 */
	private static String zeroPad(int length, String toPad) {
		int numberOfZeroes = length - toPad.length();
		for (int counter = 0; counter < numberOfZeroes; counter++) {
			toPad = "0" + toPad;
		}
		return toPad;
	}

	/**
	 * 获得随机数
	 * 
	 * @return
	 * @author GeYanmeng
	 * @date 2013-6-24
	 */
	public static String Random() {
		java.util.Random r = new java.util.Random();
		String random = Integer.toString(r.nextInt());
		if (random.length() >= 4) {
			random = random.substring(random.length() - 4, random.length());
		}
		return random;
	}
	
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		System.out.println
		("客官您好，" + calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH)+1) + "月"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "日，您在”"+"“已成功订餐，订单总金额为" +
				"元，到时带上您的钱财，凭二维码领餐吧。扫描时使用二维码小图即可，放大后可能会不易识别哦。"+"如需查看订单详细，请访问");
		
//		String filesPath="D:\\mms\\02.txt;D:\\mms\\03.txt;D:\\mms\\4.jpg";
//		String smilPath="D:\\mms\\";
//		try {
//			String result = SmilUtils.createSmilFile(filesPath,smilPath,"");
//			System.out.println(result);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}


}
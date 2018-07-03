package app.cn.qtt.bm.common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.cache.CodeTable;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.SystemConfig;
import app.cn.qtt.bm.common.utils.SystemUtility;


/**
 * @title 根据代码类型和名称显示代码显示值
 * @descriptor
 * @author zy
 * @version 2012-5-23
 */
public class ShowCodeValueTag extends TagSupport {
	private static final long serialVersionUID = 5836497816536400465L;
	protected CCrppLog4j log = new CCrppLog4j(ShowCodeValueTag.class.getName());
	
	private SystemConfig conf = new SystemConfig();

	protected String codeName = null;
	protected String codeValue = null;
	protected String styleClass = null;
	protected String style = null;
	protected String dateSource = null;

	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuffer strb = new StringBuffer();
		String showValue = "";
		try {
			if ("parm".equals(dateSource)) { // 从参数取
				showValue = CacheConstants.cache.getParameterValueByName(codeName);
			} else if ("prop".equals(dateSource)) { // 从配置文件取
				showValue = conf.parseParam(codeName, false);
			} else { // 从代码表取
				CodeTable codeTable = CacheConstants.cache.getCodeTable(
						codeName);
				for (int ii = 0,sizeNo = codeTable.size(); ii < sizeNo; ii++) {
					if (codeValue.equals(codeTable.getString(ii, "CODENAME"))) {
						showValue = codeTable.getString(ii, "CODEVALUE");
						break;
					}
				}
			}
			if (SystemUtility.isEmpty(styleClass)
					&& SystemUtility.isEmpty(style)) {
				strb.append(showValue);
			} else {
				strb.append("<a class=\"").append(
						!SystemUtility.isEmpty(styleClass) ? styleClass + "\""
								: "").append(" style=\"").append(
						!SystemUtility.isEmpty(style) ? style + "\"" : "")
						.append(">").append(showValue).append("</a>");
			}
			out.println(strb.toString());
		} catch (Exception e) {
			log.exception(".doEndTag", e);
		}
		return SKIP_BODY;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getDateSource() {
		return dateSource;
	}

	public void setDateSource(String dateSource) {
		this.dateSource = dateSource;
	}
}

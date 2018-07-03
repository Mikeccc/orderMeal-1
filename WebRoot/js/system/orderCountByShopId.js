	
	var globalCategoryId='-1';

	$(document).ready(function(){
		
		globalCategoryId =firstCategoryId;
	});
	
	function setCategory(categoryId){
		if($('#category_'+categoryId).hasClass("thistab")){
			return ;
		}else{
			$('#tabs li').removeClass("thistab");
			$('#category_'+categoryId).addClass("thistab");
			globalCategoryId = categoryId;
			getCountOrder(1);
		}
	}
	
	function getCountOrder(pageNo){
		CAjax("order-manage!orderCountByShopIdAjax",
				{
					data : {
						currentPage : pageNo,
						categoryIdArr : globalCategoryId=='-1'?'':globalCategoryId
					}
				},
				function(msg){
					if(msg.isSuccess){
						
							var html='<tr style=" background:#ECFDFA"><td width="40%">菜名</td><td width="20%">价格</td><td width="20%">总数</td><td width="20%">未领取</td></tr>';
							
							if(msg.orderResponseBean!=null){
								
								$.each(msg.orderResponseBean.resultList,function(i,v){
									html+='<tr>';
									html+='<td>'+v.shopGoodsName+'</td><td>￥'+v.shopGoodsPrice+'</td><td><b class="yellow_font">x</b>'+v.shopGoodsCount+'</td><td>'+v.orderRunStatusCount+'</td>';
									html+='</tr>';
								});
							}
							
							html+='<tr><td><b>总计：</b></td><td><b class="yellow_font">￥'+msg.orderResponseBean.priceCountDaily+'</b></td><td><b class="yellow_font">x</b>'+msg.orderResponseBean.numberCountDaily+'</td><td>'+msg.orderResponseBean.noGetNumCountDaily+'</td></tr>';
							$('#orderCountView').html(html);
						
						
					}
				}
			);
	}
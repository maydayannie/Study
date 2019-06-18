<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Hello World</title>
<style>
  .file_drag_area{
    width:300px;
    height:200px;
    border:2px dashed #ccc;
    
  }
  
  .file_drag_over{
    color:#000;
    border-color:#000;
  }
  
  .file_photo{
    
    width:300px;
    height:180px; 
}

ul, li {
	margin: 0;
	padding: 0;
	list-style: none;
}
.abgne_tab {
	clear: left;
	width: 1200px;
	margin: 10px 0;
}
ul.tabs {
	width: 100%;
	height: 32px;
	border-bottom: 1px solid #999;
	border-left: 1px solid #999;
}
ul.tabs li {
	float: left;
	height: 31px;
	line-height: 31px;
	overflow: hidden;
	position: relative;
	margin-bottom: -1px;	/* 讓 li 往下移來遮住 ul 的部份 border-bottom */
	border: 1px solid #999;
	border-left: none;
	background: #e1e1e1;
}
ul.tabs li a {
	display: block;
	padding: 0 20px;
	color: #000;
	border: 1px solid #fff;
	text-decoration: none;
}
ul.tabs li a:hover {
	background: #ccc;
}
ul.tabs li.active  {
	background: #fff;
	border-bottom: 1px solid#fff;
}
ul.tabs li.active a:hover {
	background: #FF8888;
}
div.tab_container {
	clear: left;
	width: 100%;
	border: 1px solid #999;
	border-top: none;
	background: #fff;
}
div.tab_container .tab_content {
	padding: 20px;
}
div.tab_container .tab_content h2 {
	margin: 0 0 20px;
}


</style>
<script type="text/javascript">
  	$(document).ready(function() {   //載入jquery  
  		getTable();
	});
  	
  	function getTable() {
  		$.ajax({
			url : '/online-store/admin/getProducts',   //指定要進行呼叫的位址
			type : 'GET',  //請求方式，POST/GET (預設為GET)
			dataType : 'json',   //預期Server傳回的資料類型
			error : function(xhr) {   //請求失敗時執行函式
				alert('Ajax request 發生錯誤');
			},
			success : onSuccess   //請求成功時執行函式
		});
  	}
	
	function onSuccess(response) {
		
		/* var table= $('#products');
		table.empty();   //這邊要清空，否則重新reload時資料會在append一次就重複資料了 */
		
		var body = $("#bodyproducts");
		body.empty();
		for(var i = 0 ; i < response.length ; i++) {
			var jsonObj = response[i];
			var tr = $('<tr>');    //add a <tr> to a dynamic table with jquery
			tr.attr('id',jsonObj['prodId'])   //attr 設置tr的id屬性是jsonObj[prodId]
			tr.append($('<td>').append(jsonObj['prodId']));
			tr.append($('<td>').append(jsonObj['prodName']));
			tr.append($('<td>').append(jsonObj['notes']));
			tr.append($('<td>').append(jsonObj['price']));
			//tr.append($('<td>').append(jsonObj['fileName']));
			//tr.append($('<td>').append('<img src="/online-store/image/getImg/"/> {jsonObj['fileName']}'));
			var getfileName = jsonObj['fileName'];
			var result = '<img src="/online-store/image/getImg/' + getfileName + '" width="50" height="60">';			
			tr.append($('<td>').append(result));
			
			
			
			var btnEdit = $('<button>').append('Edit');
			//動態產生button，並且設置Edit顏色為紅色			
			var btnSave = $('<button>').append('Save');
			var btnCancel = $('<button>').append('Cancel');
			var btnDelete = $('<button>').append('Delete');
			
			btnEdit.attr('class','btn btn-outline-primary');  
			//attr 設置btnEdit的class屬性是btn btn-outline-primary
			btnSave.attr('class','btn btn-outline-success');
			btnCancel.attr('class','btn btn-outline-info');
			btnDelete.attr('class','btn btn-outline-danger');
			
			var btnTd = $('<td>');
			btnTd.append(btnEdit);
			btnTd.append(btnSave.hide());
			btnTd.append(btnCancel.hide());
			btnTd.append(btnDelete);
			tr.append(btnTd);
			//table.append(tr);
			body.append(tr);
			 
			(function(j) {
				btnEdit.click(function() {
					var tr = $('#'+j);
					
					tr.find('td').each (function() {
						var td = $(this);
						
						var content = td.contents().filter(function() {
							  return this.nodeType == Node.TEXT_NODE;
							}).text();
						console.log($(this).contents().get(0).nodeType)
						if($(this).contents().get(0).nodeType == Node.TEXT_NODE) {
							$(this).html($('<input>').val(content).get(0));		
						} 
					}); 
				
					$('#products').find('button').filter('.btn-outline-primary').each(function(){
						$(this).hide();
					});
					$(this).next().show();
					$(this).next().next().show();
					$(this).next().next().next().hide();
				});
				
				btnCancel.click(function() {
					var tr = $('#'+j);
					cancel(tr);
					$(this).hide();
					$(this).prev().hide();
					$('#products').find('button').filter('.btn-outline-primary').each(function(){
						$(this).show();
						$(this).next().next().next().show();
					});
				});
				
				btnSave.click(function() {  //如有要儲存圖片不能用json格式，要改用form bean寫法
					var tr = $('#'+j);
// 					var json = {
// 						prodId: "", 
// 						notes: "", 
// 						price: 0, 
// 						prodName: ""
// 					}
// 					json['prodId'] = tr.find('td:eq(0)').find('input').val(); 
// 					json['prodName'] = tr.find('td:eq(1)').find('input').val();
// 					json['notes'] = tr.find('td:eq(2)').find('input').val();
// 					json['price'] = parseInt(tr.find('td:eq(3)').find('input').val());
// 					console.log(json);
					var formData = new FormData();  
					//FormData 可以用來收集表單資訊，建構FromData實例後，利用以下的append自行加入想要的表單內容
		        	formData.append('prodId', tr.find('td:eq(0)').find('input').val());
		        	formData.append('prodName', tr.find('td:eq(1)').find('input').val());
		        	formData.append('notes', tr.find('td:eq(2)').find('input').val());
		        	formData.append('price', parseInt(tr.find('td:eq(3)').find('input').val()));
// 		        	console.log(dataURItoBlob($('#displayImg').prop('src')));
					if($('#displayImg').prop('src') != '') {
			        	formData.append('file',dataURItoBlob($('#displayImg').prop('src')));
					}
					$('#products').find('button').filter('.btn-outline-primary').each(function(){
						$(this).show();
					});
					
					let flag = confirm('Are you sure?');
					if(flag) {
						$.ajax({
							url : '/online-store/admin/saveProduct',
							method : 'POST',
// 							headers: {
// 								"Accept" : "application/json; charset=utf-8",
// 								"Content-Type": "application/json; charset=utf-8"
// 								},
// 							data : JSON.stringify(json),
							contentType:false,
        					cache:false,
        					processData:false,
							data : formData,
							error : function(xhr) {
								alert('Ajax request 發生錯誤');
							},
							success : function(response) {
								tr.find('td').each(function() {
									$(this).html($(this).find('input').val());
								});
// 								alert(response);
							}
						});
					} else {
						cancel(tr);
					}
					$(this).hide();
					$(this).next().hide();				
				});
				
				
				btnDelete.click(function(){
					var tr = $('#'+j);					
				/* 	 var formData = new FormData();  
					//FormData 可以用來收集表單資訊，建構FromData實例後，利用以下的append自行加入想要的表單內容
		        	formData.append('prodId', tr.find('td:eq(0)').val());  */					
					  var json={
							prodId: ""
					}				
					json['prodId'] = tr.find('td:eq(0)').text();   
					let flag = confirm("Are you sure to delete this product?");								
					if (flag){
						$.ajax({
							url: '/online-store/admin/adminDelProduct/'+json['prodId'],
							method: 'GET',     
							//如果用GET，則不能指定data與dataType（否則會錯），POST才需要指定data與dataType
							/* data : JSON.stringify(json),
							dataType : 'json', */
        					error : function(xhr) {					
								alert('aaa Ajax request 發生錯誤');
							},
							success : function(response) {
								/* tr.find('td').each(function() {
									$(this).html($(this).find('input').val());
								}); */
								alert('del ok');
								getTable();
// 								alert(response);
							}
						});
					}
					
				});
				
			})(jsonObj['prodId']);
			
		}
		console.log(response);
	}
	
	function cancel(tr) {
		tr.find('td').each(function() {
			$(this).html($(this).find('input').val());
		});
	}
	
		
        $(document).ready(function(){
        	$('.file_drag_area').on('dragover', function(){
        		$(this).addClass('file_drag_over');
        		return false;
        	});
        	
        	$('.file_drag_area').on('dragleave', function(evt){
        		$(this).removeClass('flag_drag_class');
        		return false;
        	});
        	
        	$('.file_drag_area').on('drop', function(event){
        		event.preventDefault();    //防止瀏覽器執行預設動作
        		event.stopPropagation();   //取消事件繼續往下傳遞
        		$(this).removeClass('file_drag_over');
        		var filesList = event.originalEvent.dataTransfer.files;  
        		//dataTransfer負責存放被拖曳的檔案，拖曳的檔案就會在event.dataTransfer.files屬性中
        		
        		console.log(filesList);
        		var reader = new FileReader();
        		//藉由 FileReader 物件，Web 應用程式能以非同步（asynchronously）方式讀取儲存在用戶端的檔案（或原始資料暫存）
        		//內容，可以使用 File 或 Blob 物件指定要讀取的資料。
        		
        		//畫面上有兩個拖拉圖片的地方，雖然功能一樣，但兩個的id還是要區分
        		//event.target.id要判斷是因為當drop後要判斷現在在做哪一個事件目標(event.target)，才不會發生將圖片拖拉到
        		//新增商品sheet，但沒有顯示，卻顯示在所有商品sheet
        		if (event.target.id === 'img1'){    
        		reader.addEventListener("load", function () {
        			var img = $('#displayImg');
        			img.prop("src",reader.result);  
        			//reader.result讀入的資料內容。只有在讀取完成之後此屬性才有效，而資料的格式則取決於是由哪一個方法進行讀取。
        			//Base64編碼是一種圖片處理格式，但之後應要轉為BLOB
        			img.show();    //顯示
        		 }, false)
        		} else if (event.target.id === 'img2'){
        			reader.addEventListener("load", function () {
            			var img = $('#displayImg2');
            			img.prop("src",reader.result);  
            			//reader.result讀入的資料內容。只有在讀取完成之後此屬性才有效，而資料的格式則取決於是由哪一個方法進行讀取。
            			//Base64編碼是一種圖片處理格式，但之後應要轉為BLOB
            			img.show();    //顯示
            		 }, false)
        		}
        	//	alert(event.target.getAttribute('id'));
        		
        		reader.readAsDataURL(filesList[0]);
        		//開始讀取指定的 Blob，讀取完成後屬性 result 將以 data: URL 格式（base64 編碼）的字串來表示讀入的資料內容。
        		//使用 DATA URI 將圖片以 Base64 編碼並內崁至網頁中，加速載入速度
        		
        		
        		var formData = new FormData();
         	//	for(var i=0; i<filesList.length; i++){
        			formData.append('file', filesList[0]);
         	//	}
        		
        		$.ajax({
        			url:"/online-store/image/uploadImg",
        			//method:"POST",
        			data:formData,
        			contentType:false,
        			cache:false,
        			processData:false,
        			success:function(data){
        				console.log(data);
        			}
        		})
        	})
        }) 
	   
        function dataURItoBlob(dataURI) {
		    // convert base64 to raw binary data held in a string
		    // doesn't handle URLEncoded DataURIs - see SO answer #6850276 for code that does this
		    var byteString = atob(dataURI.split(',')[1]);
		
		    // separate out the mime component
		    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
		
		    // write the bytes of the string to an ArrayBuffer
		    var ab = new ArrayBuffer(byteString.length);
		    var ia = new Uint8Array(ab);
		    for (var i = 0; i < byteString.length; i++) {
		        ia[i] = byteString.charCodeAt(i);
		    }
		
		    //Old Code
		    //write the ArrayBuffer to a blob, and you're done
		    //var bb = new BlobBuilder();
		    //bb.append(ab);
		    //return bb.getBlob(mimeString);
		
		    //New Code
		    return new Blob([ab], {type: mimeString});	
		}
        
        $(function(){
        	// 預設顯示第一個 Tab
        	var _showTab = 0;
        	var $defaultLi = $('ul.tabs li').eq(_showTab).addClass('active');  //作用中的頁籤加上class="active"
        	$($defaultLi.find('a').attr('href')).siblings().hide();
         
        	// 當 li 頁籤被點擊時...
        	// 若要改成滑鼠移到 li 頁籤就切換時, 把 click 改成 mouseover
        	$('ul.tabs li').click(function() {
        		// 找出 li 中的超連結 href(#id)
        		var $this = $(this),
        			_clickTab = $this.find('a').attr('href');
        		// 把目前點擊到的 li 頁籤加上 .active
        		// 並把兄弟元素中有 .active 的都移除 class
        		$this.addClass('active').siblings('.active').removeClass('active');
        		// 淡入相對應的內容並隱藏兄弟元素
        		$(_clickTab).stop(false, true).fadeIn().siblings().hide();
        		
        		getTable();  //重新整理
        		
        		return false;
        	}).find('a').focus(function(){
        		this.blur();
        	});
        });
        
        function addNewProd(){
        	var formData = new FormData();  
        	formData.append('prodId', $("#tabAdd input[name='prodId']").val());
        	formData.append('prodName', $("#tabAdd input[name='prodName']").val());
        	formData.append('notes', $("#tabAdd input[name='notes']").val());
        	formData.append('price', $("#tabAdd input[name='price']").val());
        	if($('#displayImg2').prop('src') != '') {
	        	formData.append('file',dataURItoBlob($('#displayImg2').prop('src')));
			}
        	
        	$.ajax({
				url : '/online-store/admin/saveProduct',
				method : 'POST',
				contentType:false,
				cache:false,
				processData:false,
				data : formData,
				error : function(xhr) {
					alert('Ajax request 發生錯誤');
				},
				success : function(response) {
					$('#tabAdd').find('input').each(function() {
						$(this).html($(this).find('input').val());
					
					});
				}
			});
        	
        	$("#tabAdd").find("input").val("");
        	//$("#displayImg2").removeAttr("src");
        	$("#displayImg2").attr("src","");
        	alert("新增成功");
        };
        
        
        
         function adminSearchprod(){
        	 var json={
					prodId: "",
					prodName: ""
			}				
			json['prodId'] = $("#tabSearch input[name='searchProdid']").val(); 
        	json['prodName'] = $("#tabSearch input[name='searchProdname']").val(); 
        	json['minPrice'] = $("#tabSearch input[name='searchMinprice']").val(); 
        	json['maxPrice'] = $("#tabSearch input[name='searchMaxprice']").val(); 
        //	var resobj = undefined;
        	$.ajax({
    			url : '/online-store/admin/adminSearchProducts',   //指定要進行呼叫的位址
    			type : 'POST',  //請求方式，POST/GET (預設為GET)
    			data : JSON.stringify(json),
    			dataType : 'json',   //預期Server傳回的資料類型
    			contentType : 'application/json; charset=utf-8',
    			/*  	contentType:false,
    		 	cache:false,
    		 	processData:false, */
    		 	async : true,
    			error : function(xhr) {   //請求失敗時執行函式
    				alert('Ajax request 發生錯誤');
    			},
    			success : onSearchSuccess  //請求成功時執行函式 
    		}); 
       // 	alert(resobj);
        };
         
        
        function onSearchSuccess(response){
        	/* var table = $("#searchTable");
        	table.empty(); */
        	var body = $("#bodysearchTable");
        	body.empty();
        	for(var i = 0 ; i < response.length ; i++){     	
        		var jsonObj = response[i];
        	
        		var tr = $('<tr>');   
        		tr.attr('id',jsonObj['prodId']);
        		tr.append($('<td>').append(jsonObj['prodId']));
        		tr.append($('<td>').append(jsonObj['prodName']));
        		tr.append($('<td>').append(jsonObj['notes']));
    			tr.append($('<td>').append(jsonObj['price']));
    			var getfileName = jsonObj['fileName'];
    			var result = '<img src="/online-store/image/getImg/' + getfileName + '" width="50" height="60">';			
    			tr.append($('<td>').append(result));
    			body.append(tr);

    			//table.append(tr);
        	}
        	
        };
       
		
</script>
</head>
<body>
  <div id="tabpage" class="abgne_tab">
    <ul class="tabs">
      <li onload="alert('aa')"><a href="#tabAll">所有商品</a></li>
      <li onload="alert('bb')"><a href="#tabAdd">新增商品</a></li>
      <li onload="alert('cc')"><a href="#tabSearch">Search商品</a></li>
    </ul>
    
    <div class="tab_container">
      <div id="tabAll" class="tab_content">

		<div class="container" style="width:700px;" align="center">
          <h3 class="text-center">Drag file hereaaaaaaaa</h3>
          <div class="file_drag_area" id="img1">Drag file here<img id="displayImg" style="display:none" class="file_photo"/></div>
          <div id="uploaded_file"></div>
        </div>
		
		<table id ="products" class="table table-hover" >
		  <thead>
		    <tr>
				<th>產品代號</th>
				<th>產品名稱</th>
				<th>說明</th>
				<th>價錢</th>
				<th>IMG</th>
				<th>管理動作</th>
		    </tr>
		  </thead>
		  <tbody id="bodyproducts">
		  </tbody>  
		</table>		
      </div>
        
       <div id="tabAdd" class="tab_content">
        <div class="container" style="width:700px;" align="center">
          <h3 class="text-center">商品圖片</h3>
          <div class="file_drag_area" id="img2">Drag file here<img id="displayImg2" style="display:none" class="file_photo"/></div>
          <div id="uploaded_file"></div>
        </div> 
      
      
        產品代號：<br><input type="text" name="prodId" ><br>
        產品名稱：<br><input type="text" name="prodName" ><br>
        說明：<br><input type="text" name="notes"><br>
        價錢：<br><input type="text" name="price" onkeyup="value=value.replace(/[^\d]/g,'') "><br><br>
      
		<button class="btn btn-outline-primary" onclick="addNewProd()">確定新增</button></a><br>
      </div>
      
      <div id="tabSearch" class="tab_content">
        產品代號<input type="text" name="searchProdid" size="15"> &nbsp;&nbsp;
        產品名稱關鍵字<input type="text" name="searchProdname" size="15"> &nbsp;&nbsp;
        價錢範圍<input type="text" name="searchMinprice" size="11" placeholder="輸入最小值" onkeyup="value=value.replace(/[^\d]/g,'') ">
         ~ <input type="text" name="searchMaxprice" size="11" placeholder="輸入最大值" onkeyup="value=value.replace(/[^\d]/g,'') "> 元 &nbsp;&nbsp; <br>
        <button class="btn btn-outline-primary" onclick="adminSearchprod()">查詢</button><br><br>
        
        <div>
          <table id="searchTable" class="table table-hover">
             <thead>
	            <tr>
	              <th>產品代號</th>
				  <th>產品名稱</th>
				  <th>說明</th>
				  <th>價錢</th>
				  <th>IMG</th>
	            </tr>
             </thead>
             <tbody id="bodysearchTable">
             </tbody>
          </table>     
        </div>
      </div>
    </div>
  </div>  
  	
	<a href="<c:url value="/cart/mycart/"/>"><button class="btn btn-success">My cart</button></a>
</body>
</html>
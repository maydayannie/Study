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
</style>
<script type="text/javascript">
  	$(document).ready(function() {   //載入jquery  
		$.ajax({
			url : '/online-store/admin/getProducts',   //指定要進行呼叫的位址
			type : 'GET',  //請求方式，POST/GET (預設為GET)
			dataType : 'json',   //預期Server傳回的資料類型
			error : function(xhr) {   //請求失敗時執行函式
				alert('Ajax request 發生錯誤');
			},
			success : onSuccess   //請求成功時執行函式
		});
	});
	
	function onSuccess(response) {
		
		var table= $('#products');
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
			btnEdit.attr('class','btn btn-outline-primary');  
			//attr 設置btnEdit的class屬性是btn btn-outline-primary
			btnSave.attr('class','btn btn-outline-success');
			btnCancel.attr('class','btn btn-outline-info');
			
			var btnTd = $('<td>');
			btnTd.append(btnEdit);
			btnTd.append(btnSave.hide());
			btnTd.append(btnCancel.hide());
			tr.append(btnTd);
			table.append(tr);
			 
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
				});
				
				btnCancel.click(function() {
					var tr = $('#'+j);
					cancel(tr);
					$(this).hide();
					$(this).prev().hide();
					$('#products').find('button').filter('.btn-outline-primary').each(function(){
						$(this).show();
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
        		reader.addEventListener("load", function () {
        			var img = $('#displayImg');
        			img.prop("src",reader.result);  
        			//reader.result讀入的資料內容。只有在讀取完成之後此屬性才有效，而資料的格式則取決於是由哪一個方法進行讀取。
        			//Base64編碼是一種圖片處理格式，但之後應要轉為BLOB
        			img.show();    //顯示
        		 }, false);
        		reader.readAsDataURL(filesList[0]);
        		//開始讀取指定的 Blob，讀取完成後屬性 result 將以 data: URL 格式（base64 編碼）的字串來表示讀入的資料內容。
        		//使用 DATA URI 將圖片以 Base64 編碼並內崁至網頁中，加速載入速度
        		
        		
        		var formData = new FormData();
         	//	for(var i=0; i<filesList.length; i++){
        			formData.append('file', filesList[0]);
         	//	}
        		
        		$.ajax({
        			url:"/online-store/image/uploadImg",
        			method:"POST",
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
		
</script>
</head>
<body>
    <div class="container" style="width:700px;" align="center">
      <h3 class="text-center">Drag file hereaaaaaaaa</h3>
      <div class="file_drag_area">Drag file here<img id="displayImg" style="display:none" class="file_photo"/></div>
      <div id="uploaded_file"></div>
    </div>

	<table id ="products" class="table table-hover" >
		<tr>
			<th>產品代號</th>
			<th>產品名稱</th>
			<th>說明</th>
			<th>價錢</th>
			<th>IMG</th>
			<th>管理動作</th>
		</tr>
		
		
 		<%--  <c:forEach var="pp" items="${products}">
			<tr> 
				<td>${pp.prodId}</td>
				<td>${pp.prodName}</td>
				<td>${pp.notes}</td>
				<td>${pp.price}</td>
				<td>${pp.fileName}</td>
 				<td> 
				   <a href="<c:url value="/prod/addProd/"/>${pp.prodId}"><button class="btn btn-outline-primary">編輯</button></a>
				   <a href="<c:url value="/prod/addProd/"/>${pp.prodId}"><button class="btn btn-outline-primary">刪除</button></a>
 			    </td> 
 			</tr> 
		</c:forEach>   --%>
	</table>
	
	<a href="<c:url value="/cart/mycart/"/>"><button class="btn btn-success">My cart</button></a>
</body>
</html>
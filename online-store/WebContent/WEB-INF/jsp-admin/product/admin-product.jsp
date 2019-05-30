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
				
				btnSave.click(function() {
					var tr = $('#'+j);
					var json = {
						prodId: "", 
						notes: "", 
						price: 0, 
						prodName: ""
					}
					json['prodId'] = tr.find('td:eq(0)').find('input').val(); 
					json['prodName'] = tr.find('td:eq(1)').find('input').val();
					json['notes'] = tr.find('td:eq(2)').find('input').val();
					json['price'] = parseInt(tr.find('td:eq(3)').find('input').val());
					console.log(json);
					$('#products').find('button').filter('.btn-outline-primary').each(function(){
						$(this).show();
					});
					
					let flag = confirm('Are you sure?');
					if(flag) {
						$.ajax({
							url : '/online-store/admin/saveProduct',
							type : 'POST',
							headers: {
								"Accept" : "application/json; charset=utf-8",
								"Content-Type": "application/json; charset=utf-8"
								},
							data : JSON.stringify(json),
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
			/*var btnTd = $('<td>');
			 btnTd.append(btnEdit);
			btnTd.append(btnSave.hide());
			btnTd.append(btnCancel.hide());
			tr.append(btnTd);
			table.append(tr); */
		}
		console.log(response);
	}
	
	function cancel(tr) {
		tr.find('td').each(function() {
			$(this).html($(this).find('input').val());
		});
	}
	
	
	
	/* upload = function(){
		
		var data = new FormData();
		jQuery.each(jQuery('#file')[0].files, function(i, file){
			data.append('file-'+i, file);
		});
		
		$.ajax({
			url:'fileUpload',
			data:data,
		    cache:false,
		    contentType:false,
		    processData:false,
		    type:'POST',
		    success: function(response){
		    	debugger;
		    	data = response.data;
		    }
		})
	} */
	
	/* function handleFileSelect(evt) {
	    evt.stopPropagation();
	    evt.preventDefault();    //防止瀏覽器執行預設動作

	    var files = evt.dataTransfer.files; // FileList object. 
	    //dataTransfer負責存放被拖曳的檔案，拖曳的檔案就會在event.dataTransfer.files屬性中

	    // files is a FileList of File objects. List some properties.
	    var output = [];
	    for (var i = 0, f; f = files[i]; i++) {
	      output.push('<li><strong>', escape(f.name), '</strong> (', f.type || 'n/a', ')</li>');
	    }
	    document.getElementById('list').innerHTML = '<ul>' + output.join('') + '</ul>';
	  }
	
	function handleDragOver(evt) {
	    evt.stopPropagation();
	    evt.preventDefault();    
	    evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
	  }

	  // Setup the dnd listeners.
	  var dropZone = document.getElementById('drop_zone');
	  dropZone.addEventListener('dragover', handleDragOver, false);
	  dropZone.addEventListener('drop', handleFileSelect, false); 
	
	  function previewFile() {
		  var preview = document.querySelector('#showimg');
		  var file    = document.querySelector('input[type=file]').files[0];
		  var reader  = new FileReader();

		  reader.addEventListener("load", function () {
		    preview.src = reader.result;
		  }, false);

		  if (file) {
		    reader.readAsDataURL(file);
		  }
		}  <input type="file" id="tfile" onchange="previewFile()"/><img id="showimg"/> */
		
		
        $(document).ready(function(){
        	$('.file_drag_area').on('dragover', function(){
        		$(this).addClass('file_drag_over');
        		return false;
        	});
        	
        	$('.file_drag_area').on('dragleave', function(){
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
        		reader.addEventListener("load", function () {
        			var img = $('#displayImg');
        			img.prop("src",reader.result);
        			img.show();    //顯示
        		 }, false);
        		reader.readAsDataURL(filesList[0]);
        		
        		
        		
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
	  
		
</script>
</head>
<body>
    <div class="container" style="width:700px;" align="center">
      <h3 class="text-center">Drag file hereaaaaaaaa</h3>
      <div class="file_drag_area">Drag file here<img id="displayImg" style="display:none" class="file_photo"/></div>
      <div id="uploaded_file"></div>
    </div>

	<table class="table table-hover" id="products">
		<tr>
			<th>產品<span>代號</span></th>
			<th>產品<span>名稱</span></th>
			<th>說明</th>
			<th>價錢</th>
			<th>IMG</th>
			<th>管理動作</th>
		</tr>
		
		
<%-- 		<c:forEach var="pp" items="${prod}"> --%>
<!-- 			<tr> -->
<%-- 				<td>${pp.prodId}</td> --%>
<%-- 				<td>${pp.prodName}</td> --%>
<%-- 				<td>${pp.notes}</td> --%>
<%-- 				<td>${pp.price}</td> --%>
<!-- 				<td> -->
<%-- 				   <a href="<c:url value="/prod/addProd/"/>${pp.prodId}"><button class="btn btn-outline-primary">編輯</button></a> --%>
<%-- 				   <a href="<c:url value="/prod/addProd/"/>${pp.prodId}"><button class="btn btn-outline-primary">刪除</button></a> --%>
<!-- 			    </td> -->
<!-- 			</tr> -->
<%-- 		</c:forEach> --%>
	</table>
	
	<a href="<c:url value="/cart/mycart/"/>"><button class="btn btn-success">My cart</button></a>
</body>
</html>
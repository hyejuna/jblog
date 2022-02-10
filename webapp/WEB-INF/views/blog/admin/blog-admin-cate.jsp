<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">


</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>	

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath }/${blogVo.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath }/${blogVo.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath }/${blogVo.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateListArea">
		      			<!-- 리스트 영역 -->
		      			
						
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	//로딩 전
	$(document).ready(function(){
		//리스트 출력
		fetchList();
		console.log("로딩전 리스트 요청");
	});
	
	//추가 버튼 클릭
	$("#btnArea").on("click",function(){
		console.log("추가버튼 클릭");
		
		//입력 데이터 vo로
		var name = $("[name='name']").val();
		var desc = $("[name='desc']").val();
		
		var cateVo = {
			cateName : name,
			description : desc
		};
		
		console.log(cateVo);
		
		$.ajax({
			/* 요청 */
			url : "${pageContext.request.contextPath }/${blogVo.id}/admin/addCate", //요청 보낼 주소		
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cateVo), //자바스크립트 객체를 json 형식으로 변경
			/* 응답 */
			dataType : "json",
			success : function(cateVo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(cateVo);
				render(cateVo, "up");
				//입력화면 초기화
				$("[name='name']").val("");
				$("[name='desc']").val("");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	
	//삭제 클릭
	$("#cateListArea").on("click",".btnCateDel",function(){
		console.log("삭제 클릭");
		
		var $this = $(this);
		var no = $this.data("no");
		var cnt = $this.data("cnt");
		console.log(no);
		console.log(cnt);
		
		var removeVo = {cateNo : no};
		console.log(removeVo);
		if(cnt !== 0){
			alert("삭제할 수 없습니다.")
		} else {
			$.ajax({
				
				url : "${pageContext.request.contextPath }/${blogVo.id}/admin/removeCate",		
				type : "post",
				//contentType : "application/json",
				data : {no : no},
		
				dataType : "json",
				success : function(result){
					/*성공시 처리해야될 코드 작성*/
					$("#tr"+no).remove();
					
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		}
				
		
	});
	
	
	//리스트 출력
	function fetchList(){
		$.ajax({
			
			url : "${pageContext.request.contextPath }/${blogVo.id}/admin/cateList",		
			type : "post",
			
			dataType : "json",
			success : function(cateList){
				/*성공시 처리해야될 코드 작성*/
				console.log(cateList);
				
				for(var i=0; i<cateList.length; i++){
					render(cateList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};
	//리스트 그리기
	function render(cateVo, updown){
		var str = '';
		str += '<tr id="tr'+cateVo.cateNo+'"> ';
		str += '	<td>'+cateVo.cateNo+'</td> ';
		str += '	<td>'+cateVo.cateName+'</td> ';
		str += '	<td>'+cateVo.postCnt+'</td> ';
		str += '	<td>'+cateVo.description+'</td> ';
		str += '	<td class="text-center"> ';
		str += '		<img class="btnCateDel" data-no="'+cateVo.cateNo+'" data-cnt="'+cateVo.postCnt+'" src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += '	</td> ';
		str += '</tr> ';
		
		if (updown == 'down'){
			$("#cateListArea").append(str);
		} else if (updown == 'up'){
			$("#cateListArea").prepend(str);
		} else {
			console.log("방향오류")
		}
	};
	
</script>


</html>
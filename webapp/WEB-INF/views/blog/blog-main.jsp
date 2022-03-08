<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">


					<img id="proImg" src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}">

					<div id="nick">${blogVo.userName}(${blogVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						
						<c:forEach items="${map.cateList}" var="cateVo">
							<li><a href="${pageContext.request.contextPath }/${blogVo.id}?cateNo=${cateVo.cateNo}">${cateVo.cateName }</a></li>
						</c:forEach>
						
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->

			<div id="post_area">
				<c:choose>
					<c:when test="${empty postVo1.postTitle }"> <!-- 글이 없는 경우 -->
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left">
								<strong>등록된 글이 없습니다.</strong>
							</div>
							<div id="postDate" class="text-left">
								<strong></strong>
							</div>
							<div id="postNick"></div>
						</div>

						<div id="post"></div>
					</c:when>
					<c:otherwise>
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left">
								<strong>${postVo1.postTitle }</strong>
							</div>
							<div id="postDate" class="text-left">
								<strong>${postVo1.regDate }</strong>
							</div>
							<div id="postNick">${blogVo.userName}(${blogVo.id})님</div>
						</div>
						<!-- postBox -->

						<div id="post">${postVo1.postContent }</div>
						<br>
						<div id="commentBox">
							<c:if test="${!empty authUser}">
								<table id="commentAdd" border="1">
									<colgroup>
										<col style="width: 15%;">
										<col style="">
										<col style="width: 15%;">
									</colgroup>
									<tr>
										<td>${authUser.userName }</td>
										<td>
											<input type="text" id="cmtContent" value="" style="width:600px;" 
											data-userno="${authUser.userNo }" data-postno="${postVo1.postNo }">
										</td>
										<td><button id="btnAddCmt" class="btn_m" type="submit" style="width:85px;">저장</button></td>
									</tr>
								</table>
							</c:if>
							
							<table id="commentList">
								<colgroup>
									<col style="width: 10%;">
									<col style="width: 500px;">
									<col style="width: 10%;">
									<col style="width: 5%;">
								</colgroup>

								
							
							</table>
						</div>
					</c:otherwise>
				</c:choose>
				<!-- //post -->


				<div id="list">
					<div id="listTitle" class="text-left">
						<strong>카테고리의 글</strong>
					</div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						
						<c:forEach items="${map.postList}" var="postVo" varStatus="status">
							<c:if test="${status.count != 0}">
								<tr>
									<td class="text-left"> 
										<a href="${pageContext.request.contextPath }/${blogVo.id}?cateNo=${param.cateNo}&postNum=${status.index}">
											${postVo.postTitle}
										</a>
									</td>
									<td class="text-right">${postVo.regDate}</td>
								</tr>
							</c:if>
						</c:forEach> 
					</table>

					
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->



		</div>
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>



	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	/* 코멘트 등록 */
	$("#btnAddCmt").on("click", function(){
		console.log("코멘트저장")
		var userNo = $("#cmtContent").data("userno");
		var cmtContent = $("#cmtContent").val();
		var postNo = $("#cmtContent").data("postno");
		 
		var cmtVo={
			 userNo:userNo,
			 cmtContent:cmtContent,
			 postNo:postNo
		};
		 
		console.log(cmtVo);
	});
	
	/* 코멘트 리스트 */
	$(document).ready(function(){
		//리스트 출력
		fetchList();
		console.log("로딩전 리스트 요청");
	});
	
	//리스트 출력
	function fetchList(){
		var no = $("#cmtContent").data("postno");
		$.ajax({
			url : "${pageContext.request.contextPath }/${blogVo.id}/cmtList",		
			type : "post",
			//contentType : "application/json",
			data : {postNo: no}
			
			//dataType : "json",
			/* success : function(cmtList){
				
				console.log(cmtList);
				
				for(var i=0; i<cmtList.length; i++){
					render(cmtList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			} */
		});
	};
	
	//리스트 그리기
	function render(cateVo, updown){
		var str = '';
		str += '<tr> ';
		str += '<td> 나혜주 </td> ';
		str += '<td class="text-left"> 좋은글 감사합니다. </td> ';
		str += '<td> 2022/02/11 </td> ';
		str += '<td><img id="btnCmtDel"src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td> ';
		str += '</tr> ';
				
		if (updown == 'down'){
			$("#commentList").append(str);
		} else if (updown == 'up'){
			$("#commentList").prepend(str);
		} else {
			console.log("방향오류")
		}
	};

</script>
</html>
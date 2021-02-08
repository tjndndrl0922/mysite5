<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/mysite5/assets/js/jquery/jquery-1.12.4.js"></script>
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<div id="aside">
			<h2>회원</h2>
			<ul>
				<li>회원정보</li>
				<li>로그인</li>
				<li>회원가입</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">
			
			<div id="content-head">
            	<h3>회원가입</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>회원</li>
            			<li class="last">회원가입</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="user">
				<div>
					<form  id="joinForm" action="${pageContext.request.contextPath}/user/join" method="get">
						<!-- 아이디 -->
						<div class="form-group">
							<label class="form-text" for="input-uid">아이디</label> 
							<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
							<button type="button" id="btnCheck">중복체크</button>
						</div>
						
						<p id="msg">
							<!-- 아이디 사용가능 여부 메세지 -->
								
						</p>
						
						<!-- 비밀번호 -->
						<div class="form-group">
							<label class="form-text" for="input-pass">패스워드</label> 
							<input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요"	>
						</div>

						<!-- 이메일 -->
						<div class="form-group">
							<label class="form-text" for="input-name">이름</label> 
							<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
						</div>

						<!-- //나이 -->
						<div class="form-group">
							<span class="form-text">성별</span> 
							
							<label for="rdo-male">남</label> 
							<input type="radio" id="rdo-male" name="gender" value="male" > 
							
							<label for="rdo-female">여</label> 
							<input type="radio" id="rdo-female" name="gender" value="female" > 

						</div>

						<!-- 약관동의 -->
						<div class="form-group">
							<span class="form-text">약관동의</span> 
							
							<input type="checkbox" id="chk-agree" value="" name="">
							<label for="chk-agree">서비스 약관에 동의합니다.</label> 
						</div>
						 
						<!-- 버튼영역 -->
		                <div class="button-area">
		                    <button type="submit" id="btn-submit">회원가입</button>
		                </div>
						
					</form>
				</div>
				<!-- //joinForm -->
			</div>
			<!-- //user -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
	$("#btnCheck").on("click" , function(){
		console.log("중복체크 버튼 클릭");
		var uid = $("#input-uid").val();
		var pw = $("#input-pass").val();
		console.log(uid);
		
		//ajax데이터만 받을때
		$.ajax({
			
			url : "${pageContext.request.contextPath }/user/idcheck",		
			type : "post",
			/* contentType : "application/json", */
			data : {id:uid, password:pw},
			
			dataType : "text", 
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				if(result == 'can'){
					console.log("can");
					$("#msg").html("사용할수 있는 아이디 입니다.");
				}else{
					console.log("cant");
					$('#msg').html("사용할수 없는 아이디 입니다.")
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	//폼을 submit 할때 --> 
	$("#joinForm").on("submit", function(){
		
		//패스워드 체크
		var pw = $("#input-pass").val();
		console.log(pw.length);
		
		
		
		if(pw.length < 8){
			alert("패스워드는 8글자 이상입니다.")
			return false;
		}
		
		//동의여부 체크
		var check = $("chk:agree").is(":checked");
		console.log(check);

		if(!check){
			//동의하기 checkbox 체크 안!되있으면 > 약관에 동의해주세요 --> submit진행되면 안됨, alert()	
			alert("약관에 동의해주세요");
			return false;
		}
		
		return ture;
	});
</script>

</html>
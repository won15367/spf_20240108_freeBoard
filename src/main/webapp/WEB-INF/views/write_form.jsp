<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
	<h2>글쓰기</h2>
	<form action="write">
		<table border="1" cellspacing="0" cellpadding="0" width="600">
		<tr>
			<td>제목</td>
			<td><input type="text" name="fbtitle" size="60"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="fbname" size="60"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="8" cols="50" name="fbcontent"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" value="글입력">
			<input type="button" value="글목록" onclick="javascript:window.location.href='list'">
			</td>
		</tr>
		</table>
	</form>
</body>
</html>
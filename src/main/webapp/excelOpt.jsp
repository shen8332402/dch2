<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="http://localhost:8000/dch2/excelDemo/import.do" method="post" enctype="multipart/form-data">
		<input type="file" id="upfile" name="upfile" value="选择excel文件">
		<input id="submit_form" type="submit" value="保存"/> 
	</form>
</body>
</html>
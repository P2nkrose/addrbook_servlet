<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>주소록:수정화면</title>
  <link rel="stylesheet" href="/addrbook.css" type="text/css" media="screen" />
  <script type="text/javascript">
    function delcheck() {
      result = confirm("정말로 삭제하시겠습니까 ?");

      if(result === true){
        document.frmRemove.action.value="delete";
        document.frmRemove.submit();
      }
    }
  </script>
</head>
<body>
<div align="center">
  <h2>주소록:수정화면 </h2>
  <hr>
  [<a href=/address_book/list>주소록 목록으로</a>] <br>
  <form name="frmRemove" method="post" action="/address_book/removeAction"/>
  <form name="frmInsert" method="post" action="/address_book/modifyAction">
    <input type="hidden" name="ab_id" value="${addrbook.ab_id}">
    <table border="1">
      <tr>
        <th>이 름</th>
        <td><input type="text" name="name" maxlength="15" value="${addrbook.ab_name}"></td>
      </tr>
      <tr>
        <th>email</th>
        <td><input type="email" name="email" maxlength="50" value="${addrbook.ab_email}"></td>
      </tr>
      <tr>
        <th>전화번호</th>
        <td><input type="text" name="tel" maxlength="20" value="${addrbook.ab_tel}"></td>
      </tr>
      <tr>
        <th>생 일</th>
        <td><input type="date" name="birth" value="${addrbook.ab_birth}"></td>
      </tr>
      <tr>
        <th>회 사</th>
        <td><input type="text" name="comdept" maxlength="20" value="${addrbook.ab_comdept}"></td>
      </tr>
      <tr>
        <th>메 모</th>
        <td><input type="text" name="memo" value="${addrbook.ab_memo}"></td>
      </tr>
      <tr>
        <td colspan=2 align=center>
          <input type=submit value="저장">
          <input type=reset value="취소">
          <input type="button" value="삭제" onclick="delcheck()">
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>

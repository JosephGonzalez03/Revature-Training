	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
		
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Publish Tag</small></h1>
		<hr class="book-primary">
				
		<form action="PublishTag" method="post" class="form-horizontal">
		  
		  <div class="form-group">
		    <label for="isbn13" class="col-sm-4 control-label">ISBN 13</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="isbn13" name="isbn13" placeholder="ISBN 13" required="required" value="${tag.isbn13 }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="nameTag" class="col-sm-4 control-label">Name Tag</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="nameTag" name="nameTag" placeholder="Name Tag" required="required" value="${tag.nameTag }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button formaction="PublishTag" type="submit" class="btn btn-info">Add</button>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button formaction="RemoveTag" type="submit" class="btn btn-info">Remove</button>
		    </div>
		  </div>
		</form>

	  </div>
	</header>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />

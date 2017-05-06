<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inicio</title>
<style type="text/css">
	#timeline {
  list-style: none;
  position: relative;
  width: 969px
}
#timeline:before {
  top: 0;
  bottom: 0;
  position: absolute;
  content: " ";
  width: 2px;
  background-color: #4997cd;
  left: 50%;
  margin-left: -1.5px;
}
#timeline .clearFix {
  clear: both;
  height: 0;
}
#timeline .timeline-badge {
  color: #fff;
  width: 50px;
  height: 50px;
  font-size: 1.2em;
  text-align: center;
  position: absolute;
  top: 20px;
  left: 50%;
  margin-left: -25px;
  background-color: #4997cd;
  z-index: 100;
  border-top-right-radius: 50%;
  border-top-left-radius: 50%;
  border-bottom-right-radius: 50%;
  border-bottom-left-radius: 50%;
}
#timeline .timeline-badge span.timeline-balloon-date-day {
  font-size: 1.4em;
}
#timeline .timeline-badge span.timeline-balloon-date-month {
  font-size: .7em;
  position: relative;
  top: -10px;
}
#timeline .timeline-badge.timeline-filter-movement {
  background-color: #ffffff;
  font-size: 1.7em;
  height: 35px;
  margin-left: -18px;
  width: 35px;
  top: 40px;
}
#timeline .timeline-badge.timeline-filter-movement a span {
  color: #4997cd;
  font-size: 1.3em;
  top: -1px;
}
#timeline .timeline-badge.timeline-future-movement {
  background-color: #ffffff;
  height: 35px;
  width: 35px;
  font-size: 1.7em;
  top: -16px;
  margin-left: -18px;
}
#timeline .timeline-badge.timeline-future-movement a span {
  color: #4997cd;
  font-size: .9em;
  top: 2px;
  left: 1px;
}
#timeline .timeline-movement {
  border-bottom: dashed 1px #4997cd;
  position: relative;
  width: 1000px;
  
}
#timeline .timeline-movement.timeline-movement-top {
  height: 60px;
   width: 1000px;
}
#timeline .timeline-movement .timeline-item {
  padding: 20px 0;
}
#timeline .timeline-movement .timeline-item .timeline-panel {
  border: 1px solid #d4d4d4;
  border-radius: 3px;
  background-color: #FFFFFF;
  color: #666;
  padding: 10px;
  position: relative;
  -webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
  box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
}
#timeline .timeline-movement .timeline-item .timeline-panel .timeline-panel-ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
#timeline .timeline-movement .timeline-item .timeline-panel.credits .timeline-panel-ul {
  text-align: right;
}
#timeline .timeline-movement .timeline-item .timeline-panel.credits .timeline-panel-ul li {
  color: #666;
}
#timeline .timeline-movement .timeline-item .timeline-panel.credits .timeline-panel-ul li span.importo {
  color: #468c1f;
  font-size: 1.3em;
}
#timeline .timeline-movement .timeline-item .timeline-panel.debits .timeline-panel-ul {
  text-align: left;
}
#timeline .timeline-movement .timeline-item .timeline-panel.debits .timeline-panel-ul span.importo {
  color: #e2001a;
  font-size: 1.3em;
}
</style>
</head>
<body>
	<div class="container">
	<div id="timeline"><div class="row timeline-movement timeline-movement-top">
    <div class="timeline-badge timeline-filter-movement">
        <a href="#">
            <span class="glyphicon glyphicon-time"></span>
        </a>
    </div>

</div>

<div class="row timeline-movement">

    <div class="timeline-badge">
        <span id="dia" class="timeline-balloon-date-day"></span>
        <span id="mes" class="timeline-balloon-date-month"></span>
    </div>

    <c:set var="x" scope="session" value="0"/>
    <c:forEach items="${citas}" var="p" varStatus="loop"> 
      <c:if test="${p.id%2==0}">	
      <div  class="col-sm-offset-6 col-sm-6  timeline-item">
        <div class="row">
            <div class="col-sm-offset-1 col-sm-11">
                <div class="timeline-panel debits">
                    <ul class="timeline-panel-ul">
                        <li><span class="importo">${p.paciente.nombres}</span></li>
                        <li><span class="causale">${p.descripcion}</span> </li>
                        <li><p><small class="text-muted"><i class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${p.fechaReserva}" pattern="HH:mm:ss"/></small></p> </li>
                    </ul>
                </div>

            </div>
        </div>
     </div>
   </c:if>
    
    <c:if test="${p.id%2!=0}">
    <div class="col-sm-6  timeline-item">
        <div class="row">
            <div class="col-sm-11">
                <div class="timeline-panel credits">
                    <ul class="timeline-panel-ul">
                        <li><span class="importo">${p.paciente.nombres}</span></li>
                        <li><span class="causale">${p.descripcion}</span> </li>
                        <li><p><small class="text-muted"><i class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${p.fechaReserva}" pattern="HH:mm:ss"/></small></p> </li>
                    </ul>
                </div>

            </div>
        </div>
    </div>
 </c:if>
<c:set var="x" scope="session" value="1"/>
</c:forEach>

</div>
</div>
</div>

<script type="text/javascript">

var url = "index-ajax.json";

$(document).ready(function(){
	
	var f = new Date();
	var m= f.getMonth() +1;
	var d= f.getDate();	
	if(d<2){
	var c="0"+d;
	 $('#dia').text(c);
	}else{
	$('#dia').text(d);	
	}
    $('#mes').text('ABR');   
	
});

</script>
</body>
</html>
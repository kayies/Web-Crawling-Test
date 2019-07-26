<#import "/spring.ftl" as spring />
<html lang="ko">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<head>
    <meta charset="utf-8">
</head>
<body>
	<div class="search row" style="margin: 18px">
		<div class="col-xs-2 col-sm-2">
			<select id="moveSite" onchange="javascript:changeSite(this);" class="form-control">
				<option value="SNU" >서울대병원</option>
                <option value="SAH" selected>서울아산병원</option>
                <option value="GC">GC Labs</option>
			</select>
		</div>
    </div>
	<div class="container" style="margin: 18px">
		<table class="table table-hover">
			<colgroup>
				<col width="20%">
                <col width="80%">
			</colgroup>
			<thead>
			<tr>
				<th>검사항목명</th>
				<th>참고치(단위)</th>
			</tr>
			</thead>
			<tbody>
				<#list SAH as item>
					<tr>
						<td>${item.label}</td>
						<td>${item.range}</td>
					</tr
				</#list>>
			</tbody>
		</table>
    </div>
</body>
</html>

<script type="text/javascript">
	function changeSite(obj) {
	    var selectValue = obj.value;
	    if(selectValue === 'SNU') {
            window.location = '/';
        }else if(selectValue === 'GC'){
            window.location = '/gc';
		}else {
            window.location = '/sah'
		}
	}
</script>

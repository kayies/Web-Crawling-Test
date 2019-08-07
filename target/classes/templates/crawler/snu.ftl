<#import "/spring.ftl" as spring />
<html lang="ko">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<script src="js/pagination.min.js"></script>
<head>
    <meta charset="utf-8">
</head>
<body>
	<div class="search row" style="margin: 18px">
		<div class="col-xs-2 col-sm-2">
			<select id="moveSite" onchange="javascript:changeSite(this);" class="form-control">
				<option value="SNU" selected>서울대병원</option>
                <option value="SAH">서울아산병원</option>
                <option value="GC">GC Labs</option>
			</select>
		</div>
    </div>
	<div class="container" style="margin: 18px">
		<table class="table table-hover">
            <colgroup>
                <col width="20%">
                <col width="60%">
                <col width="20%">
            </colgroup>
			<thead>
			<tr>
				<th>검사항목명</th>
				<th>참고치</th>
				<th>단위</th>
			</tr>
			</thead>
			<tbody>
				<#list SNU as item>
					<tr>
						<td>${item.label}</td>
						<td>${item.range}</td>
						<td>${item.unit}</td>
					</tr
				</#list>>
			</tbody>
		</table>
        <nav style="text-align: center;" aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>
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

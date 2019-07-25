<#import "/spring.ftl" as spring />
<html lang="ko">
<head>
    <meta charset="utf-8">
    <style>
        table {
            width: 100%;
        }
        table, th, td {
            border: 1px solid #bcbcbc;
        }
    </style>
</head>
<body>
	<form action = "">
		<div>
			<select id="moveSite" onchange="javascript:changeSite(this);">
				<option value="SNU" selected>서울대병원</option>
                <option value="GC">GC Labs</option>
			</select>
		</div>
	</form>
	<div>
		<table>
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

		}

	}

</script>

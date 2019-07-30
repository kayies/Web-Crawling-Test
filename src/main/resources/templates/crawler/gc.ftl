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
                <option value="SAH" >서울아산병원</option>
                <option value="GC" selected>GC Labs</option>
            </select>
        </div>
    </div>
	<div class="container" style="margin: 18px">
		<table class="table table-hover">
			<thead>
			<tr>
				<th>검사항목명</th>
				<th>정상 범위 (단위)</th>
			</tr>
			</thead>
			<tbody>
				<#list GC as item>
					<tr>
						<td>${item.label}</td>
						<td>${item.range}</td>
					</tr>
				</#list>
			</tbody>
		</table>
        <div id="paginationBox" style="text-align: center;">
            <ul class="pagination" >
            <#if pagination.prev >
                <li class="page-item"><a class="page-link" href="#" onClick="fn_prev('${pagination.page}', '${pagination.pageRange}', '${pagination.rangeSize}')">Previous</a></li>
            </#if>
            <#list pagination.startPage..pagination.endPage as i>
                <li class="page-item <#if pagination.page == i> active <#else >''</#if> ">
                    <a class="page-link" href="#" onClick="fn_pagination(${i}, ${pagination.pageRange}, ${pagination.rangeSize})"> ${i} </a>
                </li>
            </#list>
            <#if pagination.next>
                <li class="page-item"><a class="page-link" href="#" onClick="fn_next('${pagination.pageRange}',
                        '${pagination.pageRange}', '${pagination.rangeSize}')" >Next</a></li>
            </#if>
            </ul>
        </div>
    </div>
</body>
</html>

<script type="text/javascript">
    function changeSite(obj) {
        var selectValue = obj.value;
        if(selectValue === 'SNU') {
            window.location = '/snu';
        }else if(selectValue === 'GC'){
            window.location = '/gc';
        }else {
            window.location = '/sah'
        }
    }

    //이전 버튼 이벤트
    function fn_prev(page, pageRange, rangeSize) {
        var page = ((pageRange - 2) * rangeSize) + 1;
        var range = pageRange - 1;
        var url = "/gc";
        url = url + "?pageNo=" + page;
        url = url + "&range=" + range;
        window.location = url;
    }

    //페이지 번호 클릭
    function fn_pagination(page, pageRange, rangeSize) {
        var url = "/gc";
        url = url + "?pageNo=" + page;
        url = url + "&range=" + pageRange;
        window.location = url;
    }

    //다음 버튼 이벤트
    function fn_next(page, pageRange, rangeSize) {
        var page = parseInt((pageRange * rangeSize)) + 1;
        var range = parseInt(pageRange) + 1;
        var url = "/gc";
        url = url + "?pageNo=" + page;
        url = url + "&range=" + range;
        window.location = url;
    }
</script>


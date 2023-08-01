<#macro pagination nameCode>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item"><a class="page-link" href="/codes/${nameCode}/0">Первая
            страница</a></li>
        <#if page != 0>
        <li class="page-item"><a class="page-link" href="/codes/${nameCode}/${page-1}">${page}</a>
        </li>
    </#if>
    <li class="page-item active"><a class="page-link" href="#">${page+1}</a></li>
    <#if page != codes.getTotalPages()-1>
    <li class="page-item"><a class="page-link" href="/codes/${nameCode}/${page+1}">${page+2}</a>
    </li>
</#if>
<li class="page-item"><a class="page-link" href="/codes/${nameCode}/${codes.getTotalPages()-1}">Последняя
    страница</a></li>
</ul>
</nav>
</#macro>
<#macro pagination link list>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="/${link}/0"><i class="bi bi-chevron-double-left"></i></a></li>
            <#if page gt 0>
                <li class="page-item"><a class="page-link" href="/${link}/${page-1}"><i class="bi bi-chevron-left"></i></a>
                </li>
            </#if>
            <li class="page-item active"><a class="page-link">
                    <input id="num" type="text" value="${page+1}" size="2"/>
                    из ${list.getTotalPages()}
                    <button onclick="location.href='/${link}/'+(document.getElementById('num').value-1)">Перейти</button>
                </a></li>
            <#if list.getTotalPages() gt page+1>
                <li class="page-item"><a class="page-link" href="/${link}/${page+1}"><i class="bi bi-chevron-right"></i></a>
                </li>
            </#if>
            <li class="page-item"><a class="page-link"
                                     href="/${link}/${list.getTotalPages()-1}"><i class="bi bi-chevron-double-right"></i></a></li>
        </ul>
    </nav>
</#macro>
<#macro edit action>
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel" >Редактирование записи</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
            </div>
            <div class="modal-body">
                <form action="${action}" method="post">
                    <div>
                        <#nested>
                        <input id="id" type="hidden" name="id">
                        <input type="hidden" name="page" value="${page}">
                        <button type="submit" class="btn my-btn">Сохранить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</#macro>
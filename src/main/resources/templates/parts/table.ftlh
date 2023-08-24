<#macro colorTr>
<script>
    $(document).ready(function(){
        $('table tr').on('click', function(e) {
            $('table tr').removeClass('marked');
            $(this).addClass('marked');
        });
    });
</script>
</#macro>
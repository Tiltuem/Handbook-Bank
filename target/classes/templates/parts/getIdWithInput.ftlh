<#macro getId>
<script>
    function getIdAccount(id){
        document.getElementById("id-account").value = id;
        document.getElementById("btn-account").removeAttribute("disabled");
    }
    function getIdEntry(id){
        document.getElementById("id-entry").value = id;
        document.getElementById("btn-entry").removeAttribute("disabled");
    }
    function getIdSWBICs(id){
        document.getElementById("id-swbics").value = id;
        document.getElementById("btn-swbics").removeAttribute("disabled");
    }

</script>
</#macro>

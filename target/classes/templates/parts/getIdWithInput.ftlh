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
    function getIdRstrList(id){
        document.getElementById("id-restriction-list").value = id;
        document.getElementById("btn-restriction-list").removeAttribute("disabled");
    }
    function getId(id){
        document.getElementById("id").value = id
    }
</script>
</#macro>

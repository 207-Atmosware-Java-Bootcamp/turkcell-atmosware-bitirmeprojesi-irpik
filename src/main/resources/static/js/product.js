//Table Sort
$(document).ready(function() {
    $("#productsList").DataTable({
        'aoColumnDefs': [{
            'bSortable': false,
            'aTargets': [-1] /* 1st one, start by the right */
        }]
    });
})

//Table Delete Alert
function deleteSweetAlertProduct(url){
    swal({
        title: "Are you sure about deleting?",
        text: "Once deleted, it cannot be recovered.",
        icon: "warning",
        buttons: ["Cancel", "Delete"],
        dangerMode: true,
    })
    .then((willDelete) => {
        if (willDelete) {
            window.location.href = url;
        } else {
            swal("It is cancelled.",{
                icon: "warning",
            });
        }
    });
}
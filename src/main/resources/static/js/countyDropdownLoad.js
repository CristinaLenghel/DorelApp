$(document).ready(function(){
		loadDropdownCounty();
});

function loadDropdownCounty() {
	$.ajax({
        type : "POST",
        url : "/jsondata/counties",
        contentType: "application/json; charset=utf-8",
        dataType : 'json',
        success : function(data) {
            var html = '';
            var len = data.length;
            html += '<option selected disabled value="">Alege Judetul</option>';
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].name + '">'
                    + data[i].name
                    + '</option>';
                }
                $('#county').html(html);
            },
            error: function (e) {
            }
     });
}
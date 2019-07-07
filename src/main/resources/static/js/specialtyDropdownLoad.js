$(document).ready(function(){
		loadDropdownSpecialty();
});

function loadDropdownSpecialty() {
	$.ajax({
        type : "POST",
        url : "/jsondata/handicrafts",
        contentType: "application/json; charset=utf-8",
        dataType : 'json',
        success : function(data) {
            var html = '';
            var len = data.length;
            html += '<option disabled value="">Alege Meseria</option>';
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].name + '">'
                    + data[i].name
                    + '</option>';
                }
                $('#specialty').html(html);
            },
            error: function (e) {
            }
     });
}
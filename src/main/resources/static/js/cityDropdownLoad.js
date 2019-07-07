$(document).ready(function(){
		$("#county").change(onSelectChange);
});

function onSelectChange() {
	var selected = $("#county option:selected");

	$.ajax({
        type : "POST",
        url : "/jsondata/cities?countyname="+selected.text(),
        contentType: "application/json; charset=utf-8",
        dataType : 'json',
        success : function(data) {
            var html = '';
            var len = data.length;
            html += '<option selected disabled value="">Alege Orasul</option>';
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].name + '">'
                    + data[i].name
                    + '</option>';
                }
                $('#city').html(html);
            },
            error: function (e) {
            }
     });
}
var post = (data, callback) => { 
    if(! callback){callback = () => {console.log("ok")}}
    
    $.ajax( {
	type: "POST",
	url: "",
	data: JSON.stringify(data),
	success: callback,
	contentType: "application/json; charset=utf-8",
	dataType: "json"
    });
}
// true is necessary for firefox only
var reload = () => {
    location.reload(true);
};
//----------------------------------
$(".doc-text").change( e => {
    var $this = $(e.currentTarget);
    post({"value": $this.val(), "path": $this.data("path"), "type":$this.data("type")});
});

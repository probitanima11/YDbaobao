var ydbaobao = {};

ydbaobao.ajax = function(o) {
	if (o.method === undefined || o.url === undefined || o.success === undefined) {
		return;
	}
	var req = new XMLHttpRequest();
	req.onreadystatechange = function() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				o.success(req);
			} else {
				document.body.innerHTML = req.responseText;
			}
		}
	};
	if (o.async === undefined) o.async = true;
	req.open(o.method, o.url, o.async);
	if (o.method.toLowerCase() == "post" || o.method.toLowerCase() == "put") {
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	}
	req.send(o.param);
};
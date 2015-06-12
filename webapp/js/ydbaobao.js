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
	if (o.method.toLowerCase() == 'post' || o.method.toLowerCase() == 'put' || o.method.toLowerCase() == 'delete') {
		req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	}
	req.send(o.param);
};

ydbaobao.createElement = function(o) {
	var el = document.createElement(o.name);
	if (o.attrs !== undefined) {
		for ( var attr in o.attrs) {
			if (o.attrs.hasOwnProperty(attr)) {
				el.setAttribute(attr, o.attrs[attr]);
			}
		}
	}
	if (o.content) {
		el.innerHTML = o.content;
	}
	return el;
};

Element.prototype.remove = function() {
	if (this.parentElement !== null)
		this.parentElement.removeChild(this);
};

Element.prototype.show = function() {
	this.style.display = "block";
};

Element.prototype.hide = function() {
	this.style.display = "none";
};

function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    return !(charCode > 31 && (charCode < 48 || charCode > 57));
}
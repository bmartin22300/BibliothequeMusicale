window.onload=generateHead();
window.onload=generateHeader();

function generateHead() {
	fetch('head.html').then((response) => {
		return response.text();
	}).then((html) => {
		document.head.innerHTML = html     
	});
}

function generateHeader() {
	fetch('header.html').then((response) => {
		return response.text();
	}).then((html) => {
		document.body.innerHTML = html     
	});
}
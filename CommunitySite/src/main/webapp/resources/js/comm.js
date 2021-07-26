/**
 * 스크립트 기반 POST 전송
 * @param action - URL
 * @param params - JSON
 * @returns
 * 호출 : sendPost('list.jsp',{"p":1,"b":10,"s":10});
 */

function sendPost(action, params) {

	var form = document.createElement('form');

	form.setAttribute('method', 'post');

	form.setAttribute('action', action);

	document.charset = "utf-8";

	for ( var key in params) {

		var hiddenField = document.createElement('input');

		hiddenField.setAttribute('type', 'hidden');

		hiddenField.setAttribute('name', key);

		hiddenField.setAttribute('value', params[key]);

		form.appendChild(hiddenField);

	}

	document.body.appendChild(form);

	form.submit();

}
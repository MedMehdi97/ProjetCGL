const BASE_URL = 'http://localhost:8080';

function fetchApi(url, _method = 'GET', _body = '', callback = Function(), callbackErr = Function()) {
  _body = new URLSearchParams(_body).toString();
  return fetch(BASE_URL+url+'?'+_body, {
    method: _method,
    headers: {
      'Accept': '*/*',
      'Content-Type': 'application/json'
    }
  }).then(async response => {
    if(!response.ok)
      throw new Error(`Erreur HTTP ! statut : ${response.status}`);
    return callback(response);
  })
  .catch(async e => {
    console.log(`Il y a eu un problème avec votre opération de récupération de la ressource "${url}" : ` + e.message);
    return callbackErr(e);
  })
  .finally(() => {
    console.log(`La tentative de récupération de "${url}" est terminée.`);
  });
}
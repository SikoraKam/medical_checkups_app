

const CLIENT_API_BASE_URL = "http://localhost:8080/api/getClientId";

export function getId() {
    return fetch(CLIENT_API_BASE_URL).then(response => response.json())
        .then(json => console.log(json))

}

export function getUrl() {
    return `http://localhost:3000/view-client/${getId()}`
}




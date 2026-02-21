export function printUsers(users) {

    const container = document.querySelector("#container");

    container.innerHTML = users.map(user => {

        const imageUrl = user.image && user.image.startsWith("http")
            ? user.image
            : "https://via.placeholder.com/300x250?text=No+Image";

        return `
            <article style="border:1px solid #ccc; padding:10px; margin:10px;">
                <img 
                    src="${imageUrl}" 
                    alt="${user.name}"
                    width="200"
                    onerror="this.src='https://via.placeholder.com/300x250?text=No+Image'"
                >
                <h2>${user.name}</h2>
                <p><strong>Sexo:</strong> ${user.sex}</p>
                <p><strong>Ocupación:</strong> ${user.occupation || "No registrada"}</p>
            </article>
        `;
    }).join("");

}


export function clearUsers() {
    const container = document.querySelector("#container");
    container.innerHTML = "";
}
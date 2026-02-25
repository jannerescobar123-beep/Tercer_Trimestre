

        
            let tasks = [];

            function saveTasks() {
                localStorage.setItem("tasks", JSON.stringify(tasks));
            }

            function loadTasks() {
                const data = localStorage.getItem("tasks");
                if (data) {
                    tasks = JSON.parse(data);
                }
            }

            const form = document.getElementById("task-form");
            const input = document.getElementById("task-input");
            const list = document.getElementById("task-list");
            const apiButton = document.getElementById("load-api");

            function renderTasks() {
                list.innerHTML = "";

                tasks.forEach((task, index) => {
                    const li = document.createElement("li");
                    li.textContent = task.title;

                    if (task.completed) {
                        li.classList.add("completed");
                    }

                    li.addEventListener("click", () => {
                        task.completed = !task.completed;
                        saveTasks();
                        renderTasks();
                    });

                    const deleteBtn = document.createElement("button");
                    deleteBtn.textContent = "X";

                    deleteBtn.addEventListener("click", (e) => {
                        e.stopPropagation();
                        tasks.splice(index, 1);
                        saveTasks();
                        renderTasks();
                    });

                    li.appendChild(deleteBtn);
                    list.appendChild(li);
                });
            }

            /*********************
             * EVENTOS 😬
             *********************/
            form.addEventListener("submit", (e) => {
                e.preventDefault();

                const value = input.value.trim();
                if (!value) return;

                tasks.push({
                    title: value,
                    completed: false,
                });

                input.value = "";
                saveTasks();
                renderTasks();
            });

            apiButton.addEventListener("click", async () => {
                try {
                    const response = await fetch(
                        "https://jsonplaceholder.typicode.com/todos?_limit=5",
                    );

                    const data = await response.json();

                    // Promesa innecesaria a propósito 😈
                    new Promise((resolve) => {
                        setTimeout(() => resolve(data), 1000);
                    }).then((apiTasks) => {
                        apiTasks.forEach((t) => {
                            tasks.push({
                                title: t.title,
                                completed: t.completed,
                            });
                        });

                        saveTasks();
                        renderTasks();
                    });
                } catch (error) {
                    alert("Error cargando tareas");
                }
            });

            /*********************
             * INICIALIZACIÓN 😬
             *********************/
            loadTasks();
            renderTasks();
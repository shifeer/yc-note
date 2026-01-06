function toggleForm() {
    const form = document.getElementById("noteForm");
    form.classList.toggle("hidden");
}

document.addEventListener("DOMContentLoaded", () => {

    const modal = document.getElementById("noteModal");
    const viewMode = document.getElementById("viewMode");
    const editMode = document.getElementById("editMode");

    const viewTitle = document.getElementById("viewTitle");
    const viewContent = document.getElementById("viewContent");

    const editTitle = document.getElementById("editTitle");
    const editContent = document.getElementById("editContent");

    const editBtn = document.getElementById("editBtn");
    const cancelEdit = document.getElementById("cancelEdit");

    const editForm = document.getElementById("editForm");
    const deleteForm = document.getElementById("deleteForm");

    document.querySelectorAll(".note-card").forEach(card => {
        card.addEventListener("click", () => {

            const id = card.dataset.id;
            const title = card.dataset.title;
            const content = card.dataset.content;

            // fill view
            viewTitle.textContent = title;
            viewContent.textContent = content;

            // fill edit
            editId.value = id;
            editTitle.value = title;
            editContent.value = content;

            // set form actions
            editForm.action = `/${USER_ID}/notes`;
            deleteForm.action = `/${USER_ID}/notes/${id}`;

            viewMode.classList.remove("hidden");
            editMode.classList.add("hidden");

            modal.classList.remove("hidden");
        });
    });

    editBtn.addEventListener("click", () => {
        viewMode.classList.add("hidden");
        editMode.classList.remove("hidden");
    });

    cancelEdit.addEventListener("click", () => {
        editMode.classList.add("hidden");
        viewMode.classList.remove("hidden");
    });

    // close modal
    modal.addEventListener("click", (e) => {
        if (e.target === modal) {
            modal.classList.add("hidden");
        }
    });

    document.addEventListener("keydown", (e) => {
        if (e.key === "Escape") {
            modal.classList.add("hidden");
        }
    });
});


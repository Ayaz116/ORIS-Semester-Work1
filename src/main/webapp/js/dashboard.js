document.addEventListener('DOMContentLoaded', function() {
    const hideCompletedCheckbox = document.getElementById('hideCompletedCheckbox');
    const sortSelect = document.getElementById('sortSelect');
    const taskGrid = document.getElementById('taskGrid');

    let tasks = Array.from(taskGrid.querySelectorAll('.task-card'));

    function priorityOrder(priority) {
        switch (priority) {
            case 'Высокий': return 1;
            case 'Средний': return 2;
            case 'Низкий': return 3;
            default: return 4;
        }
    }

    function parseDueDate(dateStr) {
        if (!dateStr) return Number.MAX_SAFE_INTEGER;
        return new Date(dateStr).getTime();
    }

    function sortTasks() {
        const sortBy = sortSelect.value;
        tasks.sort((a, b) => {
            if (sortBy === 'priority') {
                return priorityOrder(a.dataset.priority) - priorityOrder(b.dataset.priority);
            } else if (sortBy === 'dueDate') {
                return parseDueDate(a.dataset.duedate) - parseDueDate(b.dataset.duedate);
            } else if (sortBy === 'creationDate') {
                const aid = parseInt(a.dataset.id, 10);
                const bid = parseInt(b.dataset.id, 10);
                return bid - aid;
            }
            return 0;
        });
    }

    function filterTasks() {
        const hide = hideCompletedCheckbox.checked;
        tasks.forEach(task => {
            if (task.dataset.status === 'Завершено') {
                task.style.display = hide ? 'none' : 'block';
            } else {
                task.style.display = 'block';
            }
        });
    }

    function updateUI() {
        sortTasks();
        tasks.forEach(task => taskGrid.appendChild(task));
        filterTasks();
    }

    sortSelect.addEventListener('change', updateUI);
    hideCompletedCheckbox.addEventListener('change', updateUI);

    updateUI();
});

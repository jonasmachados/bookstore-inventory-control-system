function formatDateToDdMmYyyy(date) {

    const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
    };

    return new Intl.DateTimeFormat('pt-BR', options)
        .format(new Date(date));

}

export default formatDateToDdMmYyyy;
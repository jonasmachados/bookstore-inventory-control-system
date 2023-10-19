import { format, parse } from 'date-fns';
import { pt } from 'date-fns/locale';

function formatDateToDdMonthYyyy(date) {

    const parsedDate = parse(date,
        'dd/MM/yyyy',
        new Date());

    return format(parsedDate, 'd MMM yyyy', { locale: pt });

}

export default formatDateToDdMonthYyyy;
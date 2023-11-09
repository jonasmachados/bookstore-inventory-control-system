import pdfMake from 'pdfmake/build/pdfmake'
import pdfFonts from 'pdfmake/build/vfs_fonts'

function BookToPDF(bookList) {

    pdfMake.vfs = pdfFonts.pdfMake.vfs;

    const reportTitle = [
        {
            text: 'Relatório de Livros',
            fontSize: 15,
            bold: true,
            alignment: 'center',
            margin: [0, 20, 0, 20]
        }
    ];

    const dados = bookList.map((livro) => {
        return [
            { text: livro.id, fontSize: 9, margin: [0, 2, 0, 2] },
            { text: livro.titulo, fontSize: 9, margin: [0, 2, 0, 2] },
            { text: livro.autor, fontSize: 9, margin: [0, 2, 0, 2] },
            { text: livro.editora, fontSize: 9, margin: [0, 2, 0, 2] },
            { text: livro.anoPublicacao, fontSize: 9, margin: [0, 2, 0, 2] },
            { text: livro.estoque, fontSize: 9, margin: [0, 2, 0, 2] },
        ]
    });

    const details = [
        {
            table: {
                headerRows: 1,
                widths: ['*', '*', '*', '*', '*', '*'],
                body: [
                    [
                        {
                            text: 'Id',
                            style: 'tableHeader',
                            fontSize: 10,
                            bold: true
                        },
                        {
                            text: 'Titulo',
                            style: 'tableHeader',
                            fontSize: 10,
                            bold: true
                        },
                        {
                            text: 'Autor',
                            style: 'tableHeader',
                            fontSize: 10,
                            bold: true
                        },
                        {
                            text: 'Editora',
                            style: 'tableHeader',
                            fontSize: 10,
                            bold: true
                        },
                        {
                            text: 'Ano Publicação',
                            style: 'tableHeader',
                            fontSize: 10,
                            bold: true
                        },
                        {
                            text: 'Estoque',
                            style: 'tableHeader',
                            fontSize: 10,
                            bold: true
                        },
                    ],
                    ...dados
                ]
            },
            margin: [0, 20, 0, 20],
            alignment: 'center',
            layout: 'lightHorizontalLines'
        }
    ];

    function Rodape(currentPage, pageCount) {
        return [
            {
                text: currentPage + ' / ' + pageCount,
                alignment: 'right',
                fontSize: 9,
                margin: [0, 10, 20, 0]
            }
        ]
    }

    const docDefinitios = {
        pageSize: 'A4',
        paggeMargins: [15, 50, 15, 40],
        header: [reportTitle],
        content: [details],
        footer: Rodape
    }

    pdfMake.createPdf(docDefinitios).download();

}

export default BookToPDF;
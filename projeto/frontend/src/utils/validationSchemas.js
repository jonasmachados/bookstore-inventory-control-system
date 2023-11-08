import * as yup from 'yup';

export const bookRegistrationSchema = yup.object({
    titulo: yup.string()
        .min(3, 'O título deve ter no mínimo 3 caracteres.')
        .max(50, 'O título não pode ter mais de 50 caracteres.')
        .required('O título é obrigatório.'),

    autor: yup.string()
        .min(3, 'O autor deve ter no mínimo 3 caracteres.')
        .max(50, 'O autor não pode ter mais de 50 caracteres.')
        .required('O autor é obrigatório.'),

    editora: yup.string()
        .min(3, 'A editora deve ter no mínimo 3 caracteres.')
        .max(50, 'A editora não pode ter mais de 50 caracteres.')
        .required('A editora é obrigatória.'),

    linkImg: yup.string()
        .required('O link da imagem é obrigatório.')
        .url('O link da imagem é inválido.'),

    anoPublicacao: yup.string()
        .required('A data de publicação é obrigatória.'),

    estoque: yup.number()
        .typeError('A quantidade de estoque é obrigatória.')
        .integer('A quantidade de estoque deve ser um número inteiro.')
        .required('A quantidade de estoque é obrigatória.'),
});

export const customerRegistrationSchema = yup.object({
    nome: yup.string()
        .min(3, 'O nome deve ter no mínimo 3 caracteres.')
        .max(50, 'O nome não pode ter mais de 50 caracteres.')
        .required('O nome é obrigatório.'),

    rua: yup.string()
        .min(3, 'A rua deve ter no mínimo 3 caracteres.')
        .max(50, 'A rua não pode ter mais de 50 caracteres.')
        .required('A rua é obrigatório.'),

    numero: yup.number()
        .typeError('O número é obrigatório.')
        .integer('O número deve ser maior que 0.')
        .required('O númeo é obrigatório.'),

    bairro: yup.string()
        .min(3, 'O bairro deve ter no mínimo 3 caracteres.')
        .max(30, 'O bairro não pode ter mais de 30 caracteres.')
        .required('O bairro é obrigatório.'),

    cidade: yup.string()
        .min(3, 'A cidade deve ter no mínimo 3 caracteres.')
        .max(30, 'A cidade não pode ter mais de 30 caracteres.')
        .required('A cidade é obrigatória.'),

    estado: yup.string()
        .min(2, 'O estado deve ter no mínimo 2 caracteres.')
        .max(20, 'O estado não pode ter mais de 20 caracteres.')
        .required('O estoque é obrigatório.'),

    cep: yup.string()
        .min(7, 'O cep deve ter no mínimo 7 caracteres.')
        .max(11, 'O cep não pode ter mais de 11 caracteres.')
        .required('O cep é obrigatório.'),

    rg: yup.string().when('$personaType', {
        is: 'PF',
        then: yup.string()
            .min(9, 'O RG deve ter no mínimo 9 caracteres.')
            .max(12, 'O RG não pode ter mais de 12 caracteres.')
            .required('O RG é obrigatório.'),
    }),

    cpf: yup.string().when('$personaType', {
        is: 'PF',
        then: yup.string()
            .min(11, 'O CPF deve ter 11 caracteres.')
            .max(15, 'O CPF deve ter 15 caracteres.')
            .required('O CPF é obrigatório.'),
    }),

    cnpj: yup.string().when('$personaType', {
        is: 'PJ',
        then: yup.string()
            .min(14, 'O CNPJ deve ter 14 caracteres.')
            .max(20, 'O CNPJ deve ter 14 caracteres.')
            .required('O CNPJ é obrigatório.'),
    }),

});

export const purchaseRegistrationSchema = yup.object({
    bookId: yup.string()
        .typeError("Necessário selecionar um livro.")
        .required("Necessário selecionar um livro."),

    qtdItens: yup.number()
        .typeError('A quantidade é obrigatória.')
        .positive("A Quantidade deve ser maior que 0")
        .required("A quantidade é obrigatória."),

    precoVenda: yup.number()
        .typeError("O preço é obrigatório.")
        .positive("O preço deve ser maior que 0")
        .required("O preço é obrigatório.")
});

export const saleRegistrationSchema = yup.object({
    customerName: yup.string()
        .typeError("Necessário selecionar um cliente.")
        .required("Necessário selecionar um cliente."),

    bookId: yup.string()
        .typeError("Necessário selecionar um livro.")
        .required("Necessário selecionar um livro."),

    qtdItens: yup.number()
        .typeError('A quantidade é obrigatória.')
        .positive("A Quantidade deve ser maior que 0")
        .required("A quantidade é obrigatória."),

    salePrice: yup.number()
        .typeError("O preço é obrigatório.")
        .positive("O preço deve ser maior que 0")
        .required("O preço é obrigatório.")
});

export const validateForm = async (values, schema, personaType) => {
    try {
        await schema.validate(values, { context: { personaType }, abortEarly: false });
        return {};
    } catch (error) {
        const validationErrors = {};
        error.inner.forEach(err => {
            validationErrors[err.path] = err.message;
        });
        return validationErrors;
    }
};

export const renderValidationMessage = (validationErrors, field) => {
    if (validationErrors[field]) {
        return (
            <div className="error-message">
                {validationErrors[field]}
            </div>
        );
    }
    return null;
};

export const clearError = (validationErrors, setValidationErrors, field) => {
    if (validationErrors[field]) {
        setValidationErrors((errors) => ({ ...errors, [field]: '' }));
    }
};

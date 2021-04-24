export type Exception = {
    title: string,
    status: number,
    message: string
}

export type ValidationException = {
    title: string,
    status: number,
    fields: string[],
    fieldsMessage: string[];
}
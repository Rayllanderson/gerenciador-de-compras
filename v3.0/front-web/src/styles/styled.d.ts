import 'styled-components';

declare module 'styled-components' {
    export interface DefaultTheme {
        title: string,
        colors: {
            background: string,
            backgroundSecondary: string,
            primary: string,
            reversePrimary: string,
            text: string,
            textSecondary: string
        }
    }
}
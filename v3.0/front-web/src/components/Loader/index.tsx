interface LoaderProps {
    size?: 'sm' | 'md';
}

export function LoaderCircle({size}: LoaderProps) {
    const spinnerSize = size === 'sm' ? 'spinner-border-sm' : ''
    return (
        <div className="d-flex justify-content-center">
            <div className={`spinner-border ${spinnerSize} text-light`} role="status"/>
        </div>
    );
}
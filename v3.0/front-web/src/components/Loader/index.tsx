interface LoaderProps {
    size?: 'sm' | 'md';
}

export function CircleLoader({size}: LoaderProps) {
    const spinnerSize = size === 'sm' ? 'spinner-border-sm' : ''
    return (
        <div className="d-flex justify-content-center">
            <div className={`spinner-border ${spinnerSize} text-light`} role="status"/>
        </div>
    );
}

export function BallLoader({size}: LoaderProps) {
    const spinnerSize = size === 'sm' ? 'spinner-grow-sm' : ''
    return (
            <div className={`spinner-grow ${spinnerSize} text-light mx-1`} role="status"/>
    );
}
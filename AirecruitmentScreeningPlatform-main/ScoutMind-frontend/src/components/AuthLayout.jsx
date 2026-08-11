const AuthLayout = ({ children, title, subtitle }) => {
    return (
        <div className="auth-layout-container">
            <div className="auth-card-frame">

                {/* Left Panel: Form */}
                <div className="auth-left-panel">
                    <div className="brand-badge">ScoutMind</div>

                    <div className="auth-header">
                        <h1 className="auth-title">{title}</h1>
                        <p className="auth-subtitle">{subtitle}</p>
                    </div>

                    <div className="auth-form-content">
                        {children}
                    </div>

                    <div className="auth-footer">
                        <span>Terms & Conditions</span>
                    </div>
                </div>

                {/* Right Panel: Image & Floating Elements */}
                <div className="auth-right-panel">
                    <div className="auth-image-container">
                        <img
                            src="https://images.unsplash.com/photo-1552664730-d307ca884978?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80"
                            alt="ScoutMind Talent Matching"
                            className="auth-image"
                        />

                        {/* Floating Glass Cards */}
                        <div className="overlay-card top-left">
                            <div className="overlay-title">🎯 Smart Matching</div>
                            <div className="overlay-subtitle">AI-powered talent scouting</div>
                        </div>

                        <div className="overlay-card bottom-center">
                            <div className="overlay-title">✨ Top Candidate</div>
                            <div className="overlay-subtitle">Ready for next opportunity</div>
                        </div>

                    </div>
                </div>

            </div>
        </div>
    );
};

export default AuthLayout;

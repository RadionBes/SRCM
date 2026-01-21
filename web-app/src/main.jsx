import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import './css/reset.css'
import './css/index.css'
import {LeftPanel} from "./leftPanel.jsx";
import {MainBody} from "./mainBody.jsx";

const containerStyle = {
    display: 'flex',
    minHeight: '100vh'
};

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <div style={containerStyle}>
            <LeftPanel></LeftPanel>
            <MainBody></MainBody>
        </div>
    </StrictMode>
)

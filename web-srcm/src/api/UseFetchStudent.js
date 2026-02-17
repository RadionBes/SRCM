import {useCallback, useState} from "react";

export function useFetchStudents() {
    const [students, setStudents] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const url = 'http://localhost:8080/api/v1/students'

    const fetchStudents = useCallback(async () => {
        try {
            setLoading(true);
            const response = await fetch(url);

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            setStudents(data);
            setError(null);
        } catch (err) {
            setError(err.message);
            console.error('Ошибка при загрузке групп:', err);
        } finally {
            setLoading(false);
        }
    }, []);

    return {students, loading, error, fetchStudents};
}
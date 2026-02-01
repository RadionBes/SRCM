import {useState, useEffect, useCallback} from 'react';

export function useFetchGroups() {
    const [groups, setGroups] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchGroups = useCallback(async () => {
        try {
            setLoading(true);
            const response = await fetch('http://localhost:8080/api/v1/groups');

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            setGroups(data);
            setError(null);
        } catch (err) {
            setError(err.message);
            console.error('Ошибка при загрузке групп:', err);
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        fetchGroups();
    }, [fetchGroups]);

    return {groups, loading, error, fetchGroups};
}
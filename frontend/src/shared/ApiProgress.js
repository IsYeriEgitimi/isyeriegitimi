import { useEffect, useState } from 'react';
import axios from 'axios';


export const useApiProgress = (apiMethod, apiPath, strictPath) => {
    const [pendingApiCall, setPendingApiCall] = useState(false);

    useEffect(() => {
        let requestInterceptor, responseInterceptor;

        const updateApiProgressFor = (method, url, progressState) => {
            if (method !== apiMethod) {
                return;
            }
            if (strictPath && url === apiPath) {
                setPendingApiCall(progressState);
            } else if (!strictPath && url.startsWith(apiPath)) {
                setPendingApiCall(progressState);
            }
        }

        const registerInterceptors = () => {
            requestInterceptor = axios.interceptors.request.use((request) => {
                const { url, method } = request;
                updateApiProgressFor(method, url, true);
                return request;
            });

            responseInterceptor = axios.interceptors.response.use(
                (response) => {
                    const { url, method } = response.config;
                    updateApiProgressFor(method, url, false);
                    return response;
                }, (error) => {
                    const { url, method } = error.config;
                    updateApiProgressFor(method, url, false);
                    throw error;
                }
            );
        }

        const unregisterInterceptors = () => {
            axios.interceptors.request.eject(requestInterceptor);
            axios.interceptors.response.eject(responseInterceptor);
        }

        registerInterceptors();

        return () => {
            unregisterInterceptors();
        }
    }, [apiPath, apiMethod, strictPath]);

    return pendingApiCall;
}

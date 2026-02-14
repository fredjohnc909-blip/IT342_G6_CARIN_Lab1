const API_BASE = '/api';

function getToken() {
  return localStorage.getItem('token');
}

function getAuthHeaders() {
  const token = getToken();
  return {
    'Content-Type': 'application/json',
    ...(token ? { Authorization: `Bearer ${token}` } : {}),
  };
}

export async function register(username, email, password) {
  const res = await fetch(`${API_BASE}/auth/register`, {
    method: 'POST',
    headers: getAuthHeaders(),
    body: JSON.stringify({ username, email, password }),
  });
  const data = await res.json().catch(() => ({}));
  if (!res.ok) {
    const msg = data.error || data.message || (typeof data === 'object' && Object.keys(data).length ? Object.values(data)[0] : null);
    throw new Error(msg || (res.status === 0 || res.status >= 500 ? 'Cannot reach server. Start the backend (port 8080) and ensure MySQL is running in XAMPP.' : 'Registration failed'));
  }
  return data;
}

export async function login(email, password) {
  const res = await fetch(`${API_BASE}/auth/login`, {
    method: 'POST',
    headers: getAuthHeaders(),
    body: JSON.stringify({ email, password }),
  });
  const data = await res.json().catch(() => ({}));
  if (!res.ok) {
    const msg = data.error || data.message;
    throw new Error(msg || (res.status === 0 || res.status >= 500 ? 'Cannot reach server. Start the backend (port 8080) and ensure MySQL is running in XAMPP.' : 'Invalid email or password'));
  }
  return data;
}

export async function logout() {
  await fetch(`${API_BASE}/auth/logout`, {
    method: 'POST',
    headers: getAuthHeaders(),
  }).catch(() => {})
}

export async function getMe() {
  const res = await fetch(`${API_BASE}/user/me`, {
    method: 'GET',
    headers: getAuthHeaders(),
  });
  const data = await res.json().catch(() => ({}));
  if (!res.ok) {
    const msg = data.error || data.message;
    throw new Error(msg || (res.status === 0 || res.status >= 500 ? 'Cannot reach server. Start the backend (port 8080).' : 'Unauthorized'));
  }
  return data;
}

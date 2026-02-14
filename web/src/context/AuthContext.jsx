import { createContext, useContext, useState, useEffect } from 'react'
import { getMe, logout as logoutApi } from '../api/authApi'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const token = localStorage.getItem('token')
    if (!token) {
      setLoading(false)
      return
    }
    getMe()
      .then((data) => setUser(data))
      .catch(() => {
        localStorage.removeItem('token')
        setUser(null)
      })
      .finally(() => setLoading(false))
  }, [])

  const login = (authData) => {
    localStorage.setItem('token', authData.token)
    setUser({
      id: authData.id,
      username: authData.username,
      email: authData.email,
      createdAt: authData.createdAt,
    })
  }

  const logout = () => {
    logoutApi().finally(() => {})
    localStorage.removeItem('token')
    setUser(null)
  }

  const value = { user, loading, login, logout, setUser }
  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
  const ctx = useContext(AuthContext)
  if (!ctx) throw new Error('useAuth must be used within AuthProvider')
  return ctx
}

import { Outlet } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { Link } from 'react-router-dom'
import './Layout.css'

export default function Layout() {
  const { user } = useAuth()

  return (
    <div className="layout">
      <header className="header">
        <Link to="/" className="logo">IT342 Auth</Link>
        <nav>
          {user ? (
            <Link to="/dashboard">Dashboard</Link>
          ) : (
            <>
              <Link to="/login">Login</Link>
              <Link to="/register">Register</Link>
            </>
          )}
        </nav>
      </header>
      <main className="main">
        <Outlet />
      </main>
    </div>
  )
}

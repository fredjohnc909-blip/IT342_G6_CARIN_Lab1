import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { getMe } from '../api/authApi'

export default function Dashboard() {
  const navigate = useNavigate()
  const { user, logout, setUser } = useAuth()
  const [profile, setProfile] = useState(user || null)
  const [loading, setLoading] = useState(!user)

  useEffect(() => {
    if (user) {
      setProfile(user)
      setLoading(false)
      return
    }
    getMe()
      .then((data) => {
        setProfile(data)
        setUser(data)
      })
      .catch(() => logout())
      .finally(() => setLoading(false))
  }, [])

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  if (loading) return <div className="loading-screen">Loading profile...</div>

  const dateJoined = profile?.createdAt
    ? new Date(profile.createdAt).toLocaleDateString(undefined, { dateStyle: 'long' })
    : '—'

  return (
    <>
      <h1 className="page-title">Dashboard / Profile</h1>
      <div className="dashboard-card">
        <h2>Profile</h2>
        <div className="profile-row">
          <span>ID</span>
          <span>{profile?.id ?? '—'}</span>
        </div>
        <div className="profile-row">
          <span>Username</span>
          <span>{profile?.username ?? '—'}</span>
        </div>
        <div className="profile-row">
          <span>Email</span>
          <span>{profile?.email ?? '—'}</span>
        </div>
        <div className="profile-row">
          <span>Date joined</span>
          <span>{dateJoined}</span>
        </div>
        <button type="button" className="btn btn-logout" onClick={handleLogout}>
          Logout
        </button>
      </div>
    </>
  )
}

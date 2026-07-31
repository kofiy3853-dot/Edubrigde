export default function Home() {
  return (
    <main style={{ minHeight: "100vh", display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", padding: "2rem" }}>
      <div style={{ textAlign: "center", maxWidth: "800px" }}>
        <h1 style={{ fontSize: "3rem", fontWeight: "bold", marginBottom: "1rem" }}>
          EduBridge OS
        </h1>
        <p style={{ fontSize: "1.25rem", color: "#737373", marginBottom: "2rem" }}>
          Enterprise education platform connecting students with universities.
        </p>
        <div style={{ display: "flex", gap: "1rem", justifyContent: "center", flexWrap: "wrap" }}>
          <a href="/login" style={{ padding: "0.75rem 1.5rem", background: "#171717", color: "#fafafa", borderRadius: "0.5rem", textDecoration: "none" }}>
            Sign In
          </a>
          <a href="/register" style={{ padding: "0.75rem 1.5rem", background: "#f5f5f5", color: "#171717", borderRadius: "0.5rem", textDecoration: "none" }}>
            Get Started
          </a>
        </div>
        <div style={{ marginTop: "3rem", display: "grid", gridTemplateColumns: "repeat(auto-fit, minmax(200px, 1fr))", gap: "1.5rem" }}>
          <div style={{ padding: "1.5rem", border: "1px solid #e5e5e5", borderRadius: "0.5rem" }}>
            <h3 style={{ fontWeight: "600", marginBottom: "0.5rem" }}>Students</h3>
            <p style={{ fontSize: "0.875rem", color: "#737373" }}>Find programs, apply to universities, track your journey.</p>
          </div>
          <div style={{ padding: "1.5rem", border: "1px solid #e5e5e5", borderRadius: "0.5rem" }}>
            <h3 style={{ fontWeight: "600", marginBottom: "0.5rem" }}>Universities</h3>
            <p style={{ fontSize: "0.875rem", color: "#737373" }}>Manage programs, admissions, and student success.</p>
          </div>
          <div style={{ padding: "1.5rem", border: "1px solid #e5e5e5", borderRadius: "0.5rem" }}>
            <h3 style={{ fontWeight: "600", marginBottom: "0.5rem" }}>Careers</h3>
            <p style={{ fontSize: "0.875rem", color: "#737373" }}>Connect education to workforce opportunities.</p>
          </div>
        </div>
      </div>
    </main>
  );
}
